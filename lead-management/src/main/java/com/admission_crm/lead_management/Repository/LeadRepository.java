package com.admission_crm.lead_management.Repository;

import com.admission_crm.lead_management.Entity.LeadManagement.Lead;
import com.admission_crm.lead_management.Entity.LeadManagement.LeadStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeadRepository extends JpaRepository<Lead, String> {

    List<Lead> findAllByStatusOrderByCreatedAtAsc(LeadStatus status);
    List<Lead> fiindAllByStatusOrderByCreatedAtAsc(LeadStatus status);
}
