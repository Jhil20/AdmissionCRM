package org.example.leadmanagement.Configuration;


import org.example.leadmanagement.Enum.LeadStatus;
import org.example.leadmanagement.Repository.LeadRepo;
import org.example.leadmanagement.Service.CounsellorQueueService;
import org.example.leadmanagement.Service.InstituteQueueService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.hibernate.internal.util.collections.ArrayHelper.forEach;

@Configuration
public class QueueConfig {
    @Bean
    public ApplicationRunner queueInitializer(
            InstituteQueueService instituteQueueService,
            CounsellorQueueService counsellorQueueService,
            LeadRepo leadRepository) {

        return args -> {
            // 1. Load queued leads into institute queues
            leadRepository.findByLeadStatusOrderByCreatedAt(LeadStatus.QUEUED)
                    .forEach(lead -> instituteQueueService.enqueueLead(lead.getAdmissionInquiry().getInstituteId(), lead.getId()));

            // 2. Load assigned leads into counsellor assigned queues
            leadRepository.findByLeadStatusOrderByCreatedAt(LeadStatus.ASSIGNED)
                    .forEach(lead -> counsellorQueueService.assignLead(lead.getAssignedTo(), lead.getId()));

            // 3. Load follow-up leads into counsellor follow-up queues
            leadRepository.findByLeadStatusOrderByCreatedAt(LeadStatus.IN_PROGRESS)
                    .forEach(lead -> counsellorQueueService.moveNextLeadToFollowUp(lead.getAssignedTo()));

            System.out.println("✅ Queues restored from database on startup");
        };
    }
}
