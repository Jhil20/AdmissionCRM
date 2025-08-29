package com.admissioncrm.authenticationservice.Repositories;

import com.admissioncrm.authenticationservice.DTO.iAdminCounsellorResponse.CounsellorResponseDTO;
import com.admissioncrm.authenticationservice.DTO.iAdminCounsellorResponse.ListResponseDTO;
import com.admissioncrm.authenticationservice.Entities.CoreEntities.User;
import com.admissioncrm.authenticationservice.Entities.CounsellorDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CounsellorRepository extends JpaRepository<CounsellorDetails, String> {

    void deleteByUserId(User user);

    @Query("SELECT new com.admissioncrm.authenticationservice.DTO.iAdminCounsellorResponse.CounsellorResponseDTO(" +
            "u.id ,u.username, u.firstName, u.lastName, u.email, u.mobileNumber, u.isActive, u.role, " +
            "c.specialization, c.bio, i.instituteName) " +
            "FROM CounsellorDetails c " +
            "JOIN c.userId u " +
            "JOIN c.institutionId i " +
            "JOIN InstituteAdminDetails ia ON ia.institutionId.id = i.id " +
            "WHERE c.institutionId.id = :institutionId")
    Page<CounsellorResponseDTO> findCounsellorByInstituteId(
            @Param("institutionId") String institutionId,
            Pageable pageable);

}
