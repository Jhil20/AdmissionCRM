package com.admissioncrm.authenticationservice.Services;

import com.admissioncrm.authenticationservice.DTO.iAdminCounsellorResponse.CounsellorResponseDTO;
import com.admissioncrm.authenticationservice.DTO.iAdminCounsellorResponse.ListResponseDTO;
import com.admissioncrm.authenticationservice.Entities.CoreEntities.Role;
import com.admissioncrm.authenticationservice.Entities.CoreEntities.User;
import com.admissioncrm.authenticationservice.Entities.InstituteAdminDetails;
import com.admissioncrm.authenticationservice.Repositories.CounsellorRepository;
import com.admissioncrm.authenticationservice.Repositories.InstituteAdminRepository;
import com.admissioncrm.authenticationservice.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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

    @Autowired
    private CounsellorRepository counselorRepository;
    public Page<CounsellorResponseDTO> getCounsellorByInstitute(String userId, Pageable pageable) {
//        InstituteAdminDetails institute = instituteAdminRepository.findById("userId").orElseThrow(() -> new RuntimeException("Institute Admin not found"));
//        String instituteId = institute.getId();
        String instituteId = instituteAdminRepository.getInstituteIdByUserId(userId);
        if (instituteId == null) {
            throw new RuntimeException("Institute Admin not found for userId: " + userId);
        }
        return counselorRepository.findCounsellorByInstituteId(instituteId,pageable);

    }

    @Transactional
    public void deleteInstituteAdmin(String userId) {
        Optional<User> existedUser = userRepository.findById(userId);
        if (existedUser.isEmpty()) {
            throw new RuntimeException("User not found for userId: ");
        }
        instituteAdminRepository.deleteByUserId(existedUser.get());

        userRepository.deleteById(userId);
    }

    @Transactional
    public void deleteCounsellor(String userId) {
        Optional<User> existedUser = userRepository.findById(userId);
        if (existedUser.isEmpty()) {
            throw new RuntimeException("User not found for userId: ");
        }
        counsellorRepository.deleteByUserId(existedUser.get());

        userRepository.deleteById(userId);
    }
}

