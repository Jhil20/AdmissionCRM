package org.example.leadmanagement.Service;

import org.example.leadmanagement.Client.AuthenticationServiceClient;
import org.example.leadmanagement.DTO.AdmissionInquiry.Response.AdmissionInquiryCreateRes;
import org.example.leadmanagement.DTO.InternalDTO.CounsellorResponse;
import org.example.leadmanagement.DTO.InternalDTO.CourseResponse;
import org.example.leadmanagement.DTO.InternalDTO.UserResponse;
import org.example.leadmanagement.DTO.Lead.Request.LeadCreationReq;
import org.example.leadmanagement.DTO.Lead.Response.LeadCreationRes;
import org.example.leadmanagement.DTO.Lead.Response.ListLeadResponse;
import org.example.leadmanagement.Entities.Leads.AdmissionInquiry;
import org.example.leadmanagement.Entities.Leads.Lead;
import org.example.leadmanagement.Entities.Leads.Query;
import org.example.leadmanagement.Enum.LeadStatus;
import org.example.leadmanagement.Exception.LeadException;
import org.example.leadmanagement.Exception.ResourceNotFound;
import org.example.leadmanagement.Repository.AdmissionInquiryRepo;
import org.example.leadmanagement.Repository.LeadRepo;
import org.example.leadmanagement.Repository.QueryRepo;
import org.example.leadmanagement.Utils.LeadQueueJob;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
@EnableScheduling
public class LeadService {

    private final Map<String,Integer> roundRobinIndex = new ConcurrentHashMap<>();

    @Autowired
    AdmissionInquiryRepo admissionInquiryRepo;
    @Autowired
    QueryRepo queryRepo;
    @Autowired
    private LeadRepo leadRepo;
    @Autowired
    private Scheduler scheduler;
    @Autowired
    private InstituteQueueService instituteQueueService;
    @Autowired
    private AuthenticationServiceClient authenticationServiceClient;
    @Autowired
    private CounsellorQueueService counsellorQueueService;

    public LeadCreationRes createLead(LeadCreationReq leadCreationReq, String userId,String token) {
        try {
            UserResponse user = authenticationServiceClient.fetchUser(userId,token);
            if(user==null)
            {
                throw new ResourceNotFound(
                        "No User exist"
                );
            }
            if((leadCreationReq.getQueryId() == null) && (leadCreationReq.getApplicationInquiryId() != null ))
            {
                Optional<AdmissionInquiry> inquiry =
                        admissionInquiryRepo.findById(leadCreationReq.getApplicationInquiryId());
                if(inquiry.isEmpty())
                {
                    throw new ResourceNotFound("No Application Inquiry exist");
                }
                Lead lead = new Lead();
                lead.setLeadStatus(LeadStatus.NEW);
                lead.setUserId(userId);

                lead.setAdmissionInquiry(inquiry.get());
                inquiry.get().getLeads().add(lead);

                Lead savedLead =
                        leadRepo.save(lead);
                admissionInquiryRepo.save(inquiry.get());

                LeadCreationRes response = new LeadCreationRes();
                response.setLeadStatus(savedLead.getLeadStatus());
                response.setLeadTrackingId(savedLead.getReferenceId());
                scheduleQueueJob(savedLead);
                return response;


            } else if ((leadCreationReq.getQueryId() != null ) && (leadCreationReq.getApplicationInquiryId() == null)) {
                Optional<Query> query =
                        queryRepo.findById(leadCreationReq.getQueryId());
                if (query.isEmpty())
                {
                    throw new ResourceNotFound("No Query exist");
                }
                AdmissionInquiry inquiry =
                        query.get().getAdmissionInquiry();
                leadCreationReq.setApplicationInquiryId(inquiry.getId());

                if(leadRepo.existsByAdmissionInquiry(inquiry))
                {
                    Lead existedLead =
                            leadRepo.findByAdmissionInquiry(inquiry);
                    if(existedLead.getLeadStatus() != LeadStatus.SOLVED && existedLead.getQuery() == null)
                    {
                        System.out.println(
                                "existed id : "+ existedLead.getId());
                        existedLead.setQuery(query.get());
                        existedLead.setReferenceId(existedLead.getReferenceId());
                        Lead savedLead =
                                leadRepo.save(existedLead);

                        LeadCreationRes response = new LeadCreationRes();
                        response.setLeadStatus(savedLead.getLeadStatus());
                        response.setLeadTrackingId(savedLead.getReferenceId());
                        return response;
                    }
                    else
                    {
                        return getLeadCreationRes(userId, query, inquiry);
                    }
                }
                else
                {
                    return getLeadCreationRes(userId, query, inquiry);
                }
            }
            else
            {
                Optional<Query> query =
                        queryRepo.findById(leadCreationReq.getQueryId());
                if(query.isEmpty())
                {
                    throw new ResourceNotFound("No Query exist");
                }

                Optional<AdmissionInquiry> inquiry = admissionInquiryRepo.findById(leadCreationReq.getApplicationInquiryId());
                if (inquiry.isEmpty())
                {
                    throw new ResourceNotFound(
                            "Admission inquiry " +
                                    "not exist");
                }
                return getLeadCreationRes(userId,query,inquiry.get());

            }
        } catch (ResourceNotFound e) {
            throw e;
        }
//        catch (DataAccessException e)
//        {
//            throw new LeadException("Lead creation Error");
//        }
//        catch(RuntimeException e)
//        {
//            throw new LeadException("Unexpected Lead creation Error");
//        }
        catch (SchedulerException e) {
            throw new RuntimeException(e);
        }
    }

    private LeadCreationRes getLeadCreationRes(String userId, Optional<Query> query, AdmissionInquiry inquiry) throws SchedulerException {
        Lead lead = new Lead();
        lead.setLeadStatus(LeadStatus.NEW);
        lead.setUserId(userId);

        lead.setAdmissionInquiry(inquiry);
        lead.setQuery(query.get());

        inquiry.getLeads().add(lead);
        query.get().getLead().add(lead);

        Lead savedLead =
                leadRepo.save(lead);
        admissionInquiryRepo.save(inquiry);
        queryRepo.save(query.get());

        LeadCreationRes response = new LeadCreationRes();
        response.setLeadStatus(savedLead.getLeadStatus());
        response.setLeadTrackingId(savedLead.getReferenceId());
        scheduleQueueJob(savedLead);
        return response;
    }

    @Scheduled(fixedRate = 60000)
    private void assignLead()
    {
        try {
            System.out.print("running assigning Lead");
            String instituteId = instituteQueueService.randomPickInstituteId();
            System.out.print(instituteId);
            if(instituteId == null)
            {
                System.out.print("institute id null");
                return;
            }

            if(instituteQueueService.IsQueueEmpty(instituteId))
            {
                System.out.print("Queue empty");
                return;
            }

            List<CounsellorResponse> counsellors = authenticationServiceClient.fetchCounsellors(instituteId);
            if(counsellors.isEmpty())
            {
                System.out.print("Counsellor Not Found");
                throw new ResourceNotFound("counsellors not found");
            }

            int index = roundRobinIndex.getOrDefault(instituteId,0);
            int total = counsellors.size();

            for (int i=0 ; i<total ; i++)
            {
                String counsellorId = counsellors.get((index + i) % total).getId();
                int workload = counsellorQueueService.getBothQueueSize(counsellorId);

                if(workload < counsellors.get((index + i) % total).getMaxLeadHandling())
                {
                    String LeadId = instituteQueueService.dequeueLead(instituteId);
                    System.out.print(LeadId);
                    Optional<Lead> existedLead = leadRepo.findByReferenceId(LeadId);
                    if(existedLead.isEmpty())
                    {
                        instituteQueueService.enqueueLead(instituteId,LeadId);
                        throw new ResourceNotFound("Lead Not Found");
                    }
                    existedLead.get().setLeadStatus(LeadStatus.ASSIGNED);
                    existedLead.get().setAssignedTo(counsellors.get((index + i) % total).getId());
                    leadRepo.save(existedLead.get());

                    counsellorQueueService.assignLead(counsellorId,LeadId);

                    roundRobinIndex.put(instituteId,(index + i) % total);
                    System.out.print("Lead assigned to : "+counsellorId);
                    break;
                }
            }
        } catch (ResourceNotFound e) {
            throw e;
        }
        catch(LeadException e)
        {
            throw new LeadException("Unexpected Lead Assigning Error");
        }

    }

    public List<ListLeadResponse> listLeads(String userId,String token)
    {
        try {
            UserResponse user = authenticationServiceClient.fetchUser(userId,token);
            if(user == null)
            {
                throw new ResourceNotFound("User Not Found");
            }
            List<Lead> leads = leadRepo.findByUserId(userId);
            List<ListLeadResponse> responseLeads = new ArrayList<>();
            for(Lead lead : leads)
            {
                ListLeadResponse leadResponse = new ListLeadResponse();
                leadResponse.setDate(lead.getUpdatedAt());
                leadResponse.setLeadId(lead.getReferenceId());
                leadResponse.setLeadStatus(lead.getLeadStatus().toString());
                if(lead.getAssignedTo() != null)
                {
                    UserResponse assignedCounsellor = authenticationServiceClient.fetchUser(lead.getAssignedTo(),token);
                    leadResponse.setAssignedTo(assignedCounsellor.getFirstName() + " " + assignedCounsellor.getLastName());
                }



                if(lead.getQuery() != null && lead.getAdmissionInquiry() != null)
                {
                    leadResponse.setLeadDescription(lead.getQuery().getQuery());
                    leadResponse.setInstituteName(lead.getAdmissionInquiry().getInstituteName());
                    leadResponse.setLeadSubject(lead.getAdmissionInquiry().getSubject() + "AND " + lead.getQuery().getQuerySubject());
                    leadResponse.setStudentName(lead.getAdmissionInquiry().getFirstName() + " " + lead.getAdmissionInquiry().getLastName());
                } else if (lead.getQuery() == null) {
                    leadResponse.setLeadDescription("No Query has been asked during Query submission so Admission Inquiry is treated as Lead");
                    leadResponse.setInstituteName(lead.getAdmissionInquiry().getInstituteName());
                    leadResponse.setLeadSubject(lead.getAdmissionInquiry().getSubject());
                    leadResponse.setStudentName(lead.getAdmissionInquiry().getFirstName() + " " + lead.getAdmissionInquiry().getLastName());
                }

                responseLeads.add(leadResponse);
            }
            return responseLeads;
        }
        catch(ResourceNotFound e)
        {
            throw e;
        }
        catch(LeadException e)
        {
            throw new LeadException("Unexpected Lead Fetching Error");
        }
    }




//    -------------------------------------------------  Private Methods  ---------------------------------------------

    private void scheduleQueueJob(Lead lead) throws SchedulerException {
        JobDetail jobDetail = JobBuilder.newJob(LeadQueueJob.class)
                .withIdentity("LeadJob-" + lead.getId())
                .usingJobData("leadId", lead.getReferenceId())
                .usingJobData("instituteId", lead.getAdmissionInquiry().getInstituteId())
                .build();

        Trigger trigger = TriggerBuilder.newTrigger()
                .startAt(Date.from(
                        lead.getCreatedAt().plusMinutes(2)
                                .atZone(ZoneId.systemDefault())
                                .toInstant()
                ))
                .build();

        scheduler.scheduleJob(jobDetail, trigger);
    }
}
