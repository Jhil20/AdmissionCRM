package com.admissioncrm.authenticationservice.Repositories;

import com.admissioncrm.authenticationservice.Entities.CounsellorDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CounsellorRepository extends JpaRepository<CounsellorDetails, String> {
}
