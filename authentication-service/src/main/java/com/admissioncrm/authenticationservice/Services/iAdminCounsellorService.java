package com.admissioncrm.authenticationservice.Services;

import com.admissioncrm.authenticationservice.DTO.ApiResponse;
import com.admissioncrm.authenticationservice.DTO.iAdminCounsellorCreation.UserCreateRequest;
import com.admissioncrm.authenticationservice.Entities.CoreEntities.Role;
import com.admissioncrm.authenticationservice.Entities.CoreEntities.User;
import com.admissioncrm.authenticationservice.Entities.CounsellorDetails;
import com.admissioncrm.authenticationservice.Entities.InstituteAdminDetails;
import com.admissioncrm.authenticationservice.ExceptionHandling.ApiException;
import com.admissioncrm.authenticationservice.ExceptionHandling.UsernameAlreadyExistsException.UsernameAlreadyExistsException;
import com.admissioncrm.authenticationservice.Repositories.CounsellorRepository;
import com.admissioncrm.authenticationservice.Repositories.InstituteAdminRepository;
import com.admissioncrm.authenticationservice.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class iAdminCounsellorService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private InstituteAdminRepository adminRepository;

    @Autowired
    private CounsellorRepository counsellorRepository;

    public ResponseEntity<?> createInstituteAdmin(UserCreateRequest request) throws UsernameAlreadyExistsException {
        return createUserFromDTO(request, Role.INSTITUTE_ADMIN);
    }

    public ResponseEntity<?> createCounsellor(UserCreateRequest request) throws UsernameAlreadyExistsException {
        return createUserFromDTO(request, Role.COUNSELOR);
    }

    private ResponseEntity<?> createUserFromDTO(UserCreateRequest request, Role role) throws UsernameAlreadyExistsException {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new UsernameAlreadyExistsException("Username " + request.getUsername() + " is already taken");
        }

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new ApiException("Email " + request.getEmail() + " is already taken");
        }

        User user = new User();
        user.setRole(role);
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setMobileNumber(request.getMobileNumber());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        String[] nameParts = request.getFullName().trim().split("\\s+", 2);
        user.setFirstName(nameParts[0]);
        user.setLastName(nameParts.length > 1 ? nameParts[1] : "");

        userRepository.save(user); // Save the user first so the ID is generated

        if (role == Role.INSTITUTE_ADMIN) {
            InstituteAdminDetails instituteAdminDetails = new InstituteAdminDetails();
            instituteAdminDetails.setUser(user);
            instituteAdminDetails.setInstitute(request.getInstitute());
            adminRepository.save(instituteAdminDetails);
        } else if (role == Role.COUNSELOR) {
            CounsellorDetails counsellorDetails = new CounsellorDetails();
            counsellorDetails.setUser(user);
            counsellorDetails.setInstitute(request.getInstitute());
            counsellorDetails.setExpertiseArea(request.getExpertiseArea());
            counsellorRepository.save(counsellorDetails);
        }

        return ResponseEntity.ok(ApiResponse.success("User created successfully"));
    }
}
