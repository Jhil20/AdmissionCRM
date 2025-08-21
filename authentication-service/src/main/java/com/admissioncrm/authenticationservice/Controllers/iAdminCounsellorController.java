package com.admissioncrm.authenticationservice.Controllers;


import com.admissioncrm.authenticationservice.DTO.iAdminCounsellorCreation.CreateCounsellorRequest;
import com.admissioncrm.authenticationservice.DTO.iAdminCounsellorCreation.CreateInstituteAdminRequest;
import com.admissioncrm.authenticationservice.DTO.iAdminCounsellorCreation.UserCreateRequest;
import com.admissioncrm.authenticationservice.DTO.iAdminCounsellorResponse.ListResponseDTO;
import com.admissioncrm.authenticationservice.ExceptionHandling.UsernameAlreadyExistsException.UsernameAlreadyExistsException;
import com.admissioncrm.authenticationservice.Repositories.UserRepository;
import com.admissioncrm.authenticationservice.Services.CustomAdminCounsellorService;
import com.admissioncrm.authenticationservice.Services.iAdminCounsellorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth/admin")
public class iAdminCounsellorController {

    @Autowired
    private iAdminCounsellorService adminCounsellorService;
    @Autowired
    CustomAdminCounsellorService customAdminCounsellorService;

    @PreAuthorize("hasRole('UNIVERSITY_ADMIN')")
    @PostMapping("/ia/create")
    public ResponseEntity<?> createInstitueAdmin (@RequestBody CreateInstituteAdminRequest  request) throws UsernameAlreadyExistsException {

        return ResponseEntity.ok(adminCounsellorService.createInstituteAdmin(request));

    }


    @PreAuthorize("hasAnyRole('UNIVERSITY_ADMIN', 'INSTITUTE_ADMIN')")
    @PostMapping("/c/create")
    public ResponseEntity<?> createCounsellor(@RequestBody CreateCounsellorRequest  request) throws UsernameAlreadyExistsException {
        return ResponseEntity.ok(adminCounsellorService.createCounsellor(request));
    }

    @PreAuthorize("hasRole('UNIVERSITY_ADMIN')")
    @GetMapping("/getAlliAdminCounsellor")
    public ResponseEntity<Page<ListResponseDTO>> getUsers(
            @RequestParam(defaultValue = "ALL") String role,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<ListResponseDTO> result = customAdminCounsellorService.getUsersByRole(role, page, size);
        return ResponseEntity.ok(result);
    }
}
