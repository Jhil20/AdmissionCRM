package com.admissioncrm.authenticationservice.Controllers;


import com.admissioncrm.authenticationservice.DTO.iAdminCounsellorResponse.CounsellorResponseDTO;
import com.admissioncrm.authenticationservice.DTO.iAdminCounsellorResponse.InstituteAdminResponseDTO;
import com.admissioncrm.authenticationservice.Services.CustomAdminCounsellorService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/university-admin")
public class UniAdminController {

    private final CustomAdminCounsellorService customAdminCounsellorService;

    @Autowired
    public UniAdminController(CustomAdminCounsellorService customAdminCounsellorService) {
        this.customAdminCounsellorService = customAdminCounsellorService;
    }

    @PreAuthorize("hasRole('UNIVERSITY_ADMIN')")
    @GetMapping("/getAllInstituteAdmins")
    public ResponseEntity<List<InstituteAdminResponseDTO>> getAllInstituteAdmins() {
        List<InstituteAdminResponseDTO> admins = customAdminCounsellorService.getAllInstituteAdmins();
        return ResponseEntity.ok(admins);
    }

    @PreAuthorize("hasRole('UNIVERSITY_ADMIN')")
    @GetMapping("/getAllCounsellors")
    public ResponseEntity<List<CounsellorResponseDTO>> getAllCounsellors() {
        List<CounsellorResponseDTO> counsellors = customAdminCounsellorService.getAllCounsellors();
        return ResponseEntity.ok(counsellors);
    }
}
