package org.example.leadmanagement.Repository;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.example.leadmanagement.Entities.Leads.AdmissionInquiry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdmissionInquiryRepo extends JpaRepository<AdmissionInquiry, String> {
    Optional<AdmissionInquiry> findByUserIdAndInstituteId(@NotNull String userId,@NotNull String instituteId);

}

