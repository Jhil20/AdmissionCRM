package com.admissioncrm.authenticationservice.Controllers;


//import com.admissioncrm.authenticationservice.Services.CustomAdminCounsellorService;
import com.admissioncrm.authenticationservice.DTO.iAdminCounsellorResponse.ListResponseDTO;
import com.admissioncrm.authenticationservice.Entities.CoreEntities.User;
import com.admissioncrm.authenticationservice.Services.CustomAdminCounsellorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/university-admin")
public class iAdminCounsellorListController {


    private final CustomAdminCounsellorService customAdminCounsellorService;

    @Autowired
    public iAdminCounsellorListController(CustomAdminCounsellorService customAdminCounsellorService) {
        this.customAdminCounsellorService = customAdminCounsellorService;
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
