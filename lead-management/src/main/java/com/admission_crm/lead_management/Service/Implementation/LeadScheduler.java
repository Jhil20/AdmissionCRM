package com.admission_crm.lead_management.Service.Implementation;

import com.admission_crm.lead_management.Entity.LeadManagement.Lead;
import com.admission_crm.lead_management.Entity.LeadManagement.LeadStatus;
import com.admission_crm.lead_management.Repository.LeadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LeadScheduler {
    @Autowired
    private LeadRepository leadRepository;
    @Autowired
    private InstitutionQueueService institutionQueueService;

    // Runs every 5 minutes
    @Scheduled(fixedRate = 5000)
    public void moveNewLeadsToQueue() {
        List<Lead> newLeads = leadRepository.findAllByStatusOrderByCreatedAtAsc(LeadStatus.NEW);

        LocalDateTime cutoff = LocalDateTime.now().minusMinutes(30);

        for (Lead lead : newLeads) {
            if (lead.getCreatedAt().isBefore(cutoff)) {
                institutionQueueService.enqueueLead(lead.getInstitutionId(), lead.getId());
                System.out.println("Lead " + lead.getId() + " moved to QUEUED");
            }
        }
    }
}
