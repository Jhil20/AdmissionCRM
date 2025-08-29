package com.admissioncrm.authenticationservice.Services;

import com.admissioncrm.authenticationservice.DTO.ApiResponse;
import com.admissioncrm.authenticationservice.DTO.iAdminCounsellorCreation.CreateCounsellorRequest;
import com.admissioncrm.authenticationservice.DTO.iAdminCounsellorCreation.CreateInstituteAdminRequest;
import com.admissioncrm.authenticationservice.DTO.iAdminCounsellorCreation.UserCreateRequest;
import com.admissioncrm.authenticationservice.Entities.CoreEntities.Role;
import com.admissioncrm.authenticationservice.Entities.CoreEntities.User;
import com.admissioncrm.authenticationservice.Entities.CounsellorDetails;
import com.admissioncrm.authenticationservice.Entities.InstituteAdminDetails;
import com.admissioncrm.authenticationservice.ExceptionHandling.ApiException;
import com.admissioncrm.authenticationservice.ExceptionHandling.UsernameAlreadyExistsException.UsernameAlreadyExistsException;
import com.admissioncrm.authenticationservice.Repositories.CounsellorRepository;
import com.admissioncrm.authenticationservice.Repositories.InstituteAdminRepository;
import com.admissioncrm.authenticationservice.Repositories.InstitutionRepository;
import com.admissioncrm.authenticationservice.Repositories.UserRepository;
import com.admissioncrm.authenticationservice.Entities.Institution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class iAdminCounsellorService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private InstituteAdminRepository adminRepository;

    @Autowired
    private InstitutionRepository institutionRepository;

    @Autowired
    private CounsellorRepository counsellorRepository;

@Transactional
public ResponseEntity<?> createInstituteAdmin(CreateInstituteAdminRequest request)
        throws UsernameAlreadyExistsException {

    System.out.println("DEBUG: Looking for institute name = [" + request.getInstituteName() + "]");

    // Step 1: Create User (common)
    User user = createUser(request, Role.INSTITUTE_ADMIN);

    // Step 2: Fetch institution
    Institution institution = institutionRepository.findByInstituteName(request.getInstituteName())
            .orElseThrow(() -> new RuntimeException("Institution not found"));

    // Step 3: Save institute admin details
    InstituteAdminDetails adminDetails = new InstituteAdminDetails();
    adminDetails.setUserId(user);
    adminDetails.setInstitutionId(institution);
    adminDetails.setDesignation(request.getDesignation());
    adminDetails.setAlternateEmail(request.getAlternateEmail());

    adminRepository.save(adminDetails);

    return ResponseEntity.ok(ApiResponse.success("Institute Admin created successfully"));
}

    @Transactional
    public ResponseEntity<?> createCounsellor(CreateCounsellorRequest request)
            throws UsernameAlreadyExistsException {

        // Step 1: Create User (common)
        User user = createUser(request, Role.COUNSELLOR);

        // Step 2: Fetch institution
        Institution institution = institutionRepository.findByInstituteName(request.getInstituteName())
                .orElseThrow(() -> new RuntimeException("Institution not found"));

        // Step 3: Save counsellor details
        CounsellorDetails counsellorDetails = new CounsellorDetails();
        counsellorDetails.setUserId(user);
        counsellorDetails.setInstitutionId(institution);
        counsellorDetails.setSpecialization(request.getSpecialization());
        counsellorDetails.setBio(request.getBio());

        counsellorRepository.save(counsellorDetails);

        return ResponseEntity.ok(ApiResponse.success("Counsellor created successfully"));
    }

    /** Common user creation logic */
    @Transactional
    protected User createUser(UserCreateRequest request, Role role) throws UsernameAlreadyExistsException {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new ApiException("Email " + request.getEmail() + " is already taken");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setMobileNumber(request.getMobileNumber());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(role);

        return userRepository.save(user);
    }
}

