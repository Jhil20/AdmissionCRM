package com.admissioncrm.authenticationservice.Repositories;

import com.admissioncrm.authenticationservice.Entities.InstituteAdminDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface InstituteAdminRepository extends JpaRepository<InstituteAdminDetails, String> {
    @Query("SELECT ia.institutionId.id FROM InstituteAdminDetails ia where ia.userId.id= :userId")
    String getInstituteIdByUserId(@Param("userId") String userId);
}
