package com.admission_crm.lead_management.Service.Implementation;

import com.admission_crm.lead_management.DTO.LeadRequest;
import com.admission_crm.lead_management.Entity.LeadManagement.Lead;
import com.admission_crm.lead_management.Entity.LeadManagement.LeadStatus;
import com.admission_crm.lead_management.Repository.LeadRepository;
import com.admission_crm.lead_management.Service.LeadService;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

@Service
public class LeadServiceImplementation implements LeadService {

    private final Map<String, Queue<String>> instituteQueues = new ConcurrentHashMap<>();

    @Autowired
    LeadRepository leadRepository;

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

    /**
     * Load queued leads from DB when application starts.
     */
    @PostConstruct
    public void loadQueuedLeads() {
        System.out.println("Reloading queued leads from Lead table...");

        List<Lead> queuedLeads = leadRepository.findAllByStatusOrderByCreatedAtAsc(LeadStatus.QUEUED);

        for (Lead lead : queuedLeads) {
            String instituteId = lead.getInstitutionId();
            instituteQueues.putIfAbsent(instituteId, new ConcurrentLinkedQueue<>());
            instituteQueues.get(instituteId).offer(lead.getId());
        }

        System.out.println("Loaded queues for " + instituteQueues.size() + " institutes.");
    }

    /**
     * Add lead to the institute's queue.
     */
    @Transactional
    public void enqueueLead(String instituteId, String leadId) {
        instituteQueues.putIfAbsent(instituteId, new ConcurrentLinkedQueue<>());
        instituteQueues.get(instituteId).offer(leadId);

        updateStatus(leadId, LeadStatus.QUEUED);
    }

    /**
     * Get and remove the next lead from the institute's queue.
     */
    @Transactional
    public String dequeueLead(String instituteId) {
        Queue<String> queue = instituteQueues.get(instituteId);
        if (queue == null || queue.isEmpty()) return null;

        String leadId = queue.poll();
        if (leadId != null) {
            updateStatus(leadId, LeadStatus.IN_PROGRESS);
        }
        return leadId;
    }

    /**
     * Mark lead as assigned.
     */
    @Transactional
    public void assignLead(String leadId) {
        updateStatus(leadId, LeadStatus.ASSIGNED);
    }

    /**
     * Mark lead as completed.
     */
    @Transactional
    public void completeLead(String leadId) {
        updateStatus(leadId, LeadStatus.COMPLETED);
    }

    /**
     * Reject lead and remove it from any queue.
     */
    @Transactional
    public void rejectLead(String leadId) {
        updateStatus(leadId, LeadStatus.REJECTED);
        instituteQueues.values().forEach(q -> q.remove(leadId));
    }

    /**
     * Internal helper to update lead status in DB.
     */
    private void updateStatus(String leadId, LeadStatus status) {
        Lead lead = leadRepository.findById(leadId)
                .orElseThrow(() -> new RuntimeException("Lead not found"));
        lead.setStatus(status);
        leadRepository.save(lead);
    }
}
