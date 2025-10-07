package org.example.leadmanagement.Service;

import org.example.leadmanagement.Client.AuthenticationServiceClient;
import org.example.leadmanagement.DTO.AdmissionInquiry.Response.AdmissionInquiryCreateRes;
import org.example.leadmanagement.DTO.InternalDTO.CounsellorResponse;
import org.example.leadmanagement.DTO.InternalDTO.CourseResponse;
import org.example.leadmanagement.DTO.Lead.Request.LeadCreationReq;
import org.example.leadmanagement.DTO.Lead.Response.LeadCreationRes;
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
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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
    private CounsellorQueueService counsellorQueueService;

    public LeadCreationRes createLead(LeadCreationReq leadCreationReq, String userId) {
        try {
            Optional<AdmissionInquiry> inquiry = admissionInquiryRepo.findById(leadCreationReq.getApplicationInquiryId());
            if(inquiry.isEmpty()) {
                throw new ResourceNotFound("Inquiry Not Found");
            }
            Optional<Query> query = Optional.empty();
            if (leadCreationReq.getQueryId() != null)
            {
                query = queryRepo.findById(leadCreationReq.getQueryId());
                if(query.isEmpty()) {
                    throw new ResourceNotFound("Query Not Found");
                }
            }

            Lead lead = new Lead();
            lead.setLeadStatus(LeadStatus.NEW);
            lead.setAdmissionInquiry(inquiry.get());
            query.ifPresent(lead::setQuery);
            lead.setUserId(userId);

            inquiry.get().getLeads().add(lead);
            query.ifPresent(value -> value.getLead().add(lead));

            admissionInquiryRepo.save(inquiry.get());
            query.ifPresent(value -> queryRepo.save(value));
            Lead savedLead = leadRepo.save(lead);

            LeadCreationRes response = new LeadCreationRes();
            response.setLeadTrackingId(savedLead.getReferenceId());
            response.setLeadStatus(savedLead.getLeadStatus());

            scheduleQueueJob(savedLead);
            return response;
        } catch (ResourceNotFound e) {
            throw e;
        }
        catch (DataAccessException e)
        {
            throw new LeadException("Lead creation Error");
        }
        catch(RuntimeException e)
        {
            throw new LeadException("Unexpected Lead creation Error");
        } catch (SchedulerException e) {
            throw new RuntimeException(e);
        }
    }

    @Scheduled(fixedRate = 60000)
    private void assignLead()
    {
        String instituteId = instituteQueueService.randomPickInstituteId();

        if(instituteQueueService.IsQueueEmpty(instituteId))
        {
            return;
        }

        List<CounsellorResponse> counsellors = authenticationServiceClient.fetchCounsellors(instituteId);
        if(counsellors.isEmpty())
        {
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
                break;
            }
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
                        lead.getCreatedAt().plusMinutes(30)
                                .atZone(ZoneId.systemDefault())
                                .toInstant()
                ))
                .build();

        scheduler.scheduleJob(jobDetail, trigger);
    }
}
