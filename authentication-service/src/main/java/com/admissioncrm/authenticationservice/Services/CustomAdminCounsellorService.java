package com.admissioncrm.authenticationservice.Services;

import com.admissioncrm.authenticationservice.DTO.iAdminCounsellorResponse.ListResponseDTO;
import com.admissioncrm.authenticationservice.Entities.CoreEntities.Role;
import com.admissioncrm.authenticationservice.Repositories.CounsellorRepository;
import com.admissioncrm.authenticationservice.Repositories.InstituteAdminRepository;
import com.admissioncrm.authenticationservice.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

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

    public Page<ListResponseDTO> getUsersByRole(String roleStr, int page, int size) {
        Role role = null;
        if (roleStr != null && !roleStr.equalsIgnoreCase("ALL")) {
            role = Role.valueOf(roleStr.toUpperCase());
        }
        return userRepository.findCounsellorsAndAdminsRaw(PageRequest.of(page, size));
    }




}

