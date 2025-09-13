package com.admissioncrm.authenticationservice.Repositories;

import com.admissioncrm.authenticationservice.DTO.iAdminCounsellorCreation.CreateCounsellorRequest;
import com.admissioncrm.authenticationservice.DTO.iAdminCounsellorCreation.CreateInstituteAdminRequest;
import com.admissioncrm.authenticationservice.DTO.iAdminCounsellorCreation.UserCreateRequest;
import com.admissioncrm.authenticationservice.DTO.iAdminCounsellorResponse.CounsellorResponseDTO;
import com.admissioncrm.authenticationservice.DTO.iAdminCounsellorResponse.InstituteAdminResponseDTO;
import com.admissioncrm.authenticationservice.DTO.iAdminCounsellorResponse.ListResponseDTO;
import com.admissioncrm.authenticationservice.Entities.CoreEntities.Role;
import com.admissioncrm.authenticationservice.Entities.CoreEntities.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByRole(Role role);
    Optional<User> findByEmail(String email);
    Optional<User> findByMobileNumber(String mobileNumber);
    boolean existsByUsernameAndIdNot(String username,String id);
    boolean existsByEmailAndIdNot(String email,String id);
    boolean existsByMobileNumberAndIdNot(String mobileNumber,String id);

    boolean existsByMobileNumber(String mobileNumber);

    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);

    boolean existsByEmail(@NotBlank(message = "Email is required") @Email(message = "Invalid email format") String email);
//    @Query("""
//        SELECT DISTINCT u
//        FROM User u
//        LEFT JOIN FETCH u.counsellorId cd
//        LEFT JOIN FETCH cd.institutionId inst1
//        LEFT JOIN FETCH u.instituteAdminId iad
//        LEFT JOIN FETCH iad.institutionId inst2
//        WHERE u.role <> com.admissioncrm.authenticationservice.Entities.CoreEntities.Role.UNIVERSITY_ADMIN
//          AND (:role IS NULL OR u.role = :role)
//        """)
//    Page<User> findAllByRoleWithDetails(@Param("role") Role role, Pageable pageable);

    @Query("""
SELECT new com.admissioncrm.authenticationservice.DTO.iAdminCounsellorResponse.ListResponseDTO(
    u.id,
    u.firstName,
    u.lastName,
    u.username,
    u.email,
    u.mobileNumber,
    u.isActive,
    u.role,
    cd.specialization,
    cd.bio,
    inst1.instituteName,
    iad.designation,
    iad.alternateEmail,
    inst2.instituteName
    )
FROM User u
LEFT JOIN CounsellorDetails cd ON cd.userId = u
LEFT JOIN cd.institutionId inst1
LEFT JOIN InstituteAdminDetails iad ON iad.userId = u
LEFT JOIN iad.institutionId inst2
WHERE u.role IN (
    com.admissioncrm.authenticationservice.Entities.CoreEntities.Role.COUNSELLOR,
    com.admissioncrm.authenticationservice.Entities.CoreEntities.Role.INSTITUTE_ADMIN
)
""")
    Page<ListResponseDTO> findCounsellorsAndAdminsRaw(Pageable pageable);

    @Query("""
SELECT new com.admissioncrm.authenticationservice.DTO.iAdminCounsellorResponse.CounsellorResponseDTO(
    u.isActive,
    u.firstName,
    u.lastName,
    u.username,
    u.email,
    u.mobileNumber,
    cd.specialization,
    cd.bio,
    inst.instituteName
    )
FROM User u
LEFT JOIN CounsellorDetails cd ON cd.userId = u
LEFT JOIN Institution inst ON cd.institutionId = inst
WHERE u.id = :userId

""")
    Optional<CounsellorResponseDTO> getCounsellorById(@Param("userId") String userId);


    @Query("""
SELECT new com.admissioncrm.authenticationservice.DTO.iAdminCounsellorResponse.InstituteAdminResponseDTO(
    u.isActive,
    u.firstName,
    u.lastName,
    u.username,
    u.email,
    u.mobileNumber,
    iad.alternateEmail,
    iad.designation,
    inst.instituteName
    )
FROM User u
LEFT JOIN InstituteAdminDetails iad ON iad.userId = u
LEFT JOIN Institution inst ON iad.institutionId = inst
WHERE u.id = :userId
""")
    Optional<InstituteAdminResponseDTO> getInstituteAdminById(@Param("userId") String userId);

    @Query("select u.role from User u where u.id = :userId")
    Role getRoleFromId(@Param("userId") String userId);
}
