package org.example.leadmanagement.Repository;

import org.example.leadmanagement.Entities.Leads.AdmissionInquiry;
import org.example.leadmanagement.Entities.Leads.Lead;
import org.example.leadmanagement.Enum.LeadStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LeadRepo extends JpaRepository<Lead, String> {

    List<Lead> findByLeadStatusOrderByCreatedAt(LeadStatus leadStatus);

    Optional<Lead> findByReferenceId(String referenceId);

    List<Lead> findByUserId(String userId);

    boolean existsByAdmissionInquiry(AdmissionInquiry admissionInquiry);

    Lead findByAdmissionInquiry(AdmissionInquiry admissionInquiry);
}
