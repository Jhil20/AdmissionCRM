package com.admissioncrm.authenticationservice.Repositories;

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
    Optional<User> findByEmail(String email);
    Optional<User> findByMobileNumber(String mobileNumber);

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
    u.firstName,
    u.email,
    u.mobileNumber,
    u.isActive,
    u.role,
    COALESCE(inst1.instituteName, inst2.instituteName)
)
FROM User u
LEFT JOIN CounsellorDetails cd ON cd.userId = u
LEFT JOIN cd.institutionId inst1
LEFT JOIN InstituteAdminDetails iad ON iad.user = u
LEFT JOIN iad.institutionId inst2
WHERE u.role <> com.admissioncrm.authenticationservice.Entities.CoreEntities.Role.UNIVERSITY_ADMIN
  AND (:role IS NULL OR u.role = :role)
""")
    Page<ListResponseDTO> findAllByRole(@Param("role") Role role, Pageable pageable);


}
