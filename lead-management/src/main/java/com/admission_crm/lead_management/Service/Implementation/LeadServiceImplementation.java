package com.admission_crm.lead_management.Service.Implementation;

import com.admission_crm.lead_management.DTO.LeadRequest;
import com.admission_crm.lead_management.Entity.LeadManagement.Lead;
import com.admission_crm.lead_management.Entity.LeadManagement.LeadStatus;
import com.admission_crm.lead_management.Exception.ResourceNotFound;
import com.admission_crm.lead_management.Repository.LeadRepository;
import com.admission_crm.lead_management.Service.LeadService;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

@Service
public class LeadServiceImplementation implements LeadService {

    private final Map<String, Queue<String>> instituteQueues = new ConcurrentHashMap<>();

    @Autowired
    LeadRepository leadRepository;
    @Autowired
    InstitutionQueueService institutionQueueService;
    @Autowired
    CounsellorQueueService counsellorQueueService;

    @Override
    @Transactional
    public void insertLead(LeadRequest leadRequest, String instituteId) {
        Lead lead = new Lead();
        lead.setInstitutionId(instituteId);
        lead.setQualification(leadRequest.getQualification());
//      courseId to be fetched now course is only mentioned
        lead.setCourseInterest(leadRequest.getCourseInterested());
        lead.setBudgetRange(leadRequest.getBudgetRange());
        lead.setDescription(leadRequest.getDescription());
        lead.setStatus(LeadStatus.NEW);
        leadRepository.save(lead);
    }

    @Override
    public void assignLead(String userId,String leadId,String institutionId) {
        Optional<Lead> existedLead = leadRepository.findById(leadId);
        if (existedLead.isEmpty())
        {
            throw new ResourceNotFound("Invalid Lead");
        }
        String VerifiedLeadId = institutionQueueService.dequeueLead(institutionId,leadId);
        
    }
}
