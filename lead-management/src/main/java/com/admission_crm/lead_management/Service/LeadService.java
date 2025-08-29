package com.admission_crm.lead_management.Service;

import com.admission_crm.lead_management.DTO.LeadRequest;
import com.admission_crm.lead_management.Entity.LeadManagement.Lead;
import org.springframework.stereotype.Service;

@Service
public interface LeadService {

    public void insertLead(LeadRequest leadRequest,String instituteId);

    public void assignLead(String userId, String leadId,String instituteId);
}
