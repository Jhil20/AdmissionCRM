package org.example.leadmanagement.Service;

import org.example.leadmanagement.DTO.AdmissionInquiry.Response.AdmissionInquiryCreateRes;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LeadService {

    @Autowired
    AdmissionInquiryRepo admissionInquiryRepo;
    @Autowired
    QueryRepo queryRepo;
    @Autowired
    private LeadRepo leadRepo;

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

            return response;
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
    }
}
