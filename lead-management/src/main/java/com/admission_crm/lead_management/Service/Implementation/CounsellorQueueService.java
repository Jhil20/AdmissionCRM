package com.admission_crm.lead_management.Service.Implementation;

import com.admission_crm.lead_management.Entity.LeadManagement.Lead;
import com.admission_crm.lead_management.Entity.LeadManagement.LeadFollowUp;
import com.admission_crm.lead_management.Entity.LeadManagement.LeadStatus;
import com.admission_crm.lead_management.Exception.ResourceNotFound;
import com.admission_crm.lead_management.Repository.LeadFollowUpRepository;
import com.admission_crm.lead_management.Repository.LeadRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.module.ResolutionException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

@Service
public class CounsellorQueueService {

    @Autowired
    private LeadFollowUpRepository leadFollowUpRepository;
    @Autowired
    private LeadRepository leadRepository;

    // In-memory queues for each counsellor
    private final Map<String, Queue<String>> assignedQueues = new ConcurrentHashMap<>();
    private final Map<String, Queue<String>> followUpQueues = new ConcurrentHashMap<>();

    /** Load existing ASSIGNED and FOLLOW_UP leads on restart */
    @PostConstruct
    public void loadLeads() {
        System.out.println("Reloading counsellor assigned and follow-up leads...");

        List<LeadFollowUp> assignedLeads = leadFollowUpRepository.findAllByOrderByCreatedAtAsc();

        for (LeadFollowUp followUp : assignedLeads) {
            String counsellorId = followUp.getCounselorId();
            String leadId = followUp.getLeadId();

            // Put into queue depending on status
            Optional<Lead> lead = leadRepository.findById(leadId);
            if (lead.isEmpty()) continue;

            if (lead.get().getStatus() == LeadStatus.ASSIGNED) {
                assignedQueues.computeIfAbsent(counsellorId, k -> new ConcurrentLinkedQueue<>()).offer(leadId);
            } else if (lead.get().getStatus() == LeadStatus.IN_PROGRESS) {
                followUpQueues.computeIfAbsent(counsellorId, k -> new ConcurrentLinkedQueue<>()).offer(leadId);
            }
        }

        System.out.println("Loaded assigned queues: " + assignedQueues.size());
        System.out.println("Loaded follow-up queues: " + followUpQueues.size());
    }

    /** Assign lead to counsellor (goes into ASSIGNED queue) */
    @Transactional
    public void assignLead(String counsellorId, String leadId) {
        assignedQueues.computeIfAbsent(counsellorId, k -> new ConcurrentLinkedQueue<>()).offer(leadId);
        updateStatus(leadId, LeadStatus.ASSIGNED);

        LeadFollowUp followUp = leadFollowUpRepository.findByLeadId(leadId)
                .orElseThrow(() -> new ResourceNotFound("Lead follow-up record not found"));

        followUp.setCounselorId(counsellorId);
        leadFollowUpRepository.save(followUp);
    }

    /** Counsellor opens lead → moves from ASSIGNED to FOLLOW_UP */
    @Transactional
    public String startFollowUp(String counsellorId) {
        Queue<String> queue = assignedQueues.get(counsellorId);
        if (queue == null || queue.isEmpty()) return null;

        String leadId = queue.poll();
        if (leadId != null) {
            followUpQueues.computeIfAbsent(counsellorId, k -> new ConcurrentLinkedQueue<>()).offer(leadId);
            updateStatus(leadId, LeadStatus.IN_PROGRESS);
        }
        return leadId;
    }

    /** Mark follow-up lead as solved */
    @Transactional
    public void solveLead(String counsellorId, String leadId) {
        Queue<String> followUps = followUpQueues.get(counsellorId);
        if (followUps != null) {
            followUps.remove(leadId);
        }
        updateStatus(leadId, LeadStatus.COMPLETED);
    }

    /** Reject lead (remove from both queues) */
    @Transactional
    public void rejectLead(String counsellorId, String leadId) {
        assignedQueues.getOrDefault(counsellorId, new ConcurrentLinkedQueue<>()).remove(leadId);
        followUpQueues.getOrDefault(counsellorId, new ConcurrentLinkedQueue<>()).remove(leadId);
        updateStatus(leadId, LeadStatus.REJECTED);
    }

    /** Internal helper to update status */
    @Transactional
    protected void updateStatus(String leadId, LeadStatus status) {
        Lead lead = leadRepository.findById(leadId)
                .orElseThrow(() -> new ResolutionException("Lead not found"));
        lead.setStatus(status);
        leadRepository.save(lead);
    }
}
