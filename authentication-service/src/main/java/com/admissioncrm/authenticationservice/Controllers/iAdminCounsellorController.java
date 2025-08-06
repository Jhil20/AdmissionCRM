package com.admissioncrm.authenticationservice.Controllers;


import com.admissioncrm.authenticationservice.DTO.iAdminCounsellorCreation.UserCreateRequest;
import com.admissioncrm.authenticationservice.ExceptionHandling.UsernameAlreadyExistsException.UsernameAlreadyExistsException;
import com.admissioncrm.authenticationservice.Repositories.UserRepository;
import com.admissioncrm.authenticationservice.Services.iAdminCounsellorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth/admin")
public class iAdminCounsellorController {

    @Autowired
    private iAdminCounsellorService adminCounsellorService;

    @PreAuthorize("hasRole('UNIVERSITY_ADMIN')")
    @PostMapping("/ia/create")
    public ResponseEntity<?> createInstitueAdmin (@RequestBody UserCreateRequest request) throws UsernameAlreadyExistsException {

        return ResponseEntity.ok(adminCounsellorService.createInstituteAdmin(request));

    }


    @PreAuthorize("hasAnyRole('UNIVERSITY_ADMIN', 'INSTITUTE_ADMIN')")
    @PostMapping("/c/create")
    public ResponseEntity<?> createCounsellor(@RequestBody UserCreateRequest request) throws UsernameAlreadyExistsException {
        return ResponseEntity.ok(adminCounsellorService.createCounsellor(request));
    }
}
