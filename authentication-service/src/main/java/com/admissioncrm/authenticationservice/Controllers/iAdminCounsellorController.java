package com.admissioncrm.authenticationservice.Controllers;


import com.admissioncrm.authenticationservice.DTO.iAdminCounsellorCreation.CreateCounsellorRequest;
import com.admissioncrm.authenticationservice.DTO.iAdminCounsellorCreation.CreateInstituteAdminRequest;
import com.admissioncrm.authenticationservice.DTO.iAdminCounsellorCreation.UserCreateRequest;
import com.admissioncrm.authenticationservice.DTO.iAdminCounsellorResponse.CounsellorResponseDTO;
import com.admissioncrm.authenticationservice.DTO.iAdminCounsellorResponse.ListResponseDTO;
import com.admissioncrm.authenticationservice.Entities.CoreEntities.User;
import com.admissioncrm.authenticationservice.ExceptionHandling.UsernameAlreadyExistsException.UsernameAlreadyExistsException;
import com.admissioncrm.authenticationservice.Repositories.UserRepository;
import com.admissioncrm.authenticationservice.Services.CustomAdminCounsellorService;
import com.admissioncrm.authenticationservice.Services.iAdminCounsellorService;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth/admin")
public class iAdminCounsellorController {

    @Autowired
    private iAdminCounsellorService adminCounsellorService;
    @Autowired
    CustomAdminCounsellorService customAdminCounsellorService;
    @Autowired
    private UserRepository userRepository;

    @PreAuthorize("hasRole('UNIVERSITY_ADMIN')")
    @PostMapping("/ia/create")
    public ResponseEntity<?> createInstitueAdmin (@RequestBody CreateInstituteAdminRequest  request) throws UsernameAlreadyExistsException {

        return ResponseEntity.ok(adminCounsellorService.createInstituteAdmin(request));

    }


    @PreAuthorize("hasAnyRole('UNIVERSITY_ADMIN', 'INSTITUTE_ADMIN')")
    @PostMapping("/c/create")
    public ResponseEntity<?> createCounsellor(@Valid @RequestBody CreateCounsellorRequest  request) throws UsernameAlreadyExistsException {
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

    @PreAuthorize("hasRole('INSTITUTE_ADMIN')")
    @GetMapping("/getCounsellorByInstitute")
    public ResponseEntity<Page<CounsellorResponseDTO>> getCounsellorByInstitute(HttpServletRequest request,@PageableDefault(size = 10, sort = "id") Pageable pageable){
        String userId = (String) request.getAttribute("userId");
        Page<CounsellorResponseDTO> counsellors= customAdminCounsellorService.getCounsellorByInstitute(userId,pageable);
        return ResponseEntity.ok(counsellors);
    }


    @PreAuthorize("hasAnyRole('UNIVERSITY_ADMIN','INSTITUTE_ADMIN')")
    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteCounsellor(@PathVariable String id) {

        User targetUser = userRepository.findById(id).orElseThrow(()-> new UsernameNotFoundException("User not found"));
        String targetRole=targetUser.getRole().name();

        switch (targetRole) {
            case "INSTITUTE_ADMIN":
                customAdminCounsellorService.deleteInstituteAdmin(id);
                return ResponseEntity.ok("Institute admin deleted successfully");
            case "COUNSELLOR":
                customAdminCounsellorService.deleteCounsellor(id);
                return ResponseEntity.ok("Counsellor deleted successfully");
            default:
                return ResponseEntity.badRequest()
                        .body("Deletion not allowed for user with role: " + targetRole);

        }
    }
}
