package com.admission_crm.lead_management.Service.Implementation;

import com.admission_crm.lead_management.Entity.LeadManagement.Lead;
import com.admission_crm.lead_management.Entity.LeadManagement.LeadStatus;
import com.admission_crm.lead_management.Exception.LeadException;
import com.admission_crm.lead_management.Repository.LeadRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

@Service
public class InstitutionQueueService {

    private final LeadRepository leadRepository;

    // in-memory queue for each institute
    private final Map<String, Queue<String>> instituteQueues = new ConcurrentHashMap<>();

    public InstitutionQueueService(LeadRepository leadRepository) {
        this.leadRepository = leadRepository;
    }

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

    /** Add lead to institute’s queue */
    @Transactional
    public void enqueueLead(String instituteId, String leadId) {
        instituteQueues.putIfAbsent(instituteId, new ConcurrentLinkedQueue<>());
        instituteQueues.get(instituteId).offer(leadId);
        updateStatus(leadId, LeadStatus.QUEUED);
    }

    /** Remove next lead from institute queue */
    @Transactional
    public String dequeueLead(String instituteId,String leadIdToVerify) {
        Queue<String> queue = instituteQueues.get(instituteId);
        if (queue == null || queue.isEmpty()) return null;
        if(!queue.peek().equals(leadIdToVerify)) {
            throw new LeadException("Invalid lead passed");
        }
        String leadId = queue.poll();
        return leadId; // status will be changed after assigning to counsellor
    }

    /** Reject lead from institution queue */
    @Transactional
    public void rejectLead(String leadId) {
        updateStatus(leadId, LeadStatus.REJECTED);
        instituteQueues.values().forEach(q -> q.remove(leadId));
    }

    private void updateStatus(String leadId, LeadStatus status) {
        Lead lead = leadRepository.findById(leadId)
                .orElseThrow(() -> new RuntimeException("Lead not found"));
        lead.setStatus(status);
        leadRepository.save(lead);
    }
}
