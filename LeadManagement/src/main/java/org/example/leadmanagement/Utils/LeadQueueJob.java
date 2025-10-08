package org.example.leadmanagement.Utils;

import org.example.leadmanagement.Entities.Leads.Lead;
import org.example.leadmanagement.Enum.LeadStatus;
import org.example.leadmanagement.Exception.ResourceNotFound;
import org.example.leadmanagement.Repository.LeadRepo;
import org.example.leadmanagement.Service.InstituteQueueService;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class LeadQueueJob implements Job {

    @Autowired
    private LeadRepo leadRepo;
    @Autowired
    InstituteQueueService instituteQueueService;


    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobDataMap dataMap = jobExecutionContext.getMergedJobDataMap();
        String leadId = (String) dataMap.get("leadId");
        String instituteId = (String) dataMap.get("instituteId");

        Optional<Lead> existedLead = leadRepo.findByReferenceId(leadId);
        if (existedLead.isEmpty()) {
            throw new ResourceNotFound("Lead not found");
        }

        existedLead.get().setLeadStatus(LeadStatus.QUEUED);
        leadRepo.save(existedLead.get());

        instituteQueueService.enqueueLead(instituteId,leadId);

    }
}
