package com.admission_crm.lead_management.Repository;

import com.admission_crm.lead_management.Entity.LeadManagement.LeadFollowUp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface LeadFollowUpRepository extends JpaRepository<LeadFollowUp, String> {

    List<LeadFollowUp> findAllByOrderByCreatedAtAsc ();
    Optional<LeadFollowUp> findByLeadId (String leadId);
}
