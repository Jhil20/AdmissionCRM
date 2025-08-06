package com.admissioncrm.authenticationservice.Services;

import com.admissioncrm.authenticationservice.DTO.iAdminCounsellorResponse.CounsellorResponseDTO;
import com.admissioncrm.authenticationservice.DTO.iAdminCounsellorResponse.InstituteAdminResponseDTO;
import com.admissioncrm.authenticationservice.Entities.CoreEntities.Role;
import com.admissioncrm.authenticationservice.Repositories.CounsellorRepository;
import com.admissioncrm.authenticationservice.Repositories.InstituteAdminRepository;
import com.admissioncrm.authenticationservice.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomAdminCounsellorService {

    private final UserRepository userRepository;
    private final CounsellorRepository counsellorRepository;
    private final InstituteAdminRepository instituteAdminRepository;

    @Autowired
    public CustomAdminCounsellorService(UserRepository userRepository, CounsellorRepository counsellorRepository, InstituteAdminRepository instituteAdminRepository) {
        this.userRepository = userRepository;
        this.counsellorRepository = counsellorRepository;
        this.instituteAdminRepository = instituteAdminRepository;
    }



    public List<InstituteAdminResponseDTO> getAllInstituteAdmins() {
        return userRepository.findByRole(Role.INSTITUTE_ADMIN).stream()
                .map(user -> {
                    return new InstituteAdminResponseDTO(
                            user.getEmail(),
                            user.getActive(),
                            user.getMobileNumber(),
                            user.getRole().name(),
                            user.getUsername(),
                            user.getInstituteAdminDetails().getInstitute() // Direct enum to string
                    );
                }).collect(Collectors.toList());
    }

    public List<CounsellorResponseDTO> getAllCounsellors() {
        return userRepository.findByRole(Role.COUNSELOR).stream()
                .map(user -> {
                    return new CounsellorResponseDTO(
                            user.getEmail(),
                            user.getActive(),
                            user.getMobileNumber(),
                            user.getRole().name(),
                            user.getUsername(),
                            user.getCounsellorDetails().getExpertiseArea(),
                            user.getCounsellorDetails().getInstitute() // Assume always filled
                    );
                }).collect(Collectors.toList());
    }
}

