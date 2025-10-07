package com.admissioncrm.authenticationservice.Controllers;


import com.admissioncrm.authenticationservice.DTO.Courses.InstituteFilterDTO;
import com.admissioncrm.authenticationservice.DTO.Courses.InstitutionResponseDTO;
import com.admissioncrm.authenticationservice.Entities.Institution;
import com.admissioncrm.authenticationservice.Enums.CategoryType;
import com.admissioncrm.authenticationservice.Enums.DegreeType;
import com.admissioncrm.authenticationservice.Services.InstitutionCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/institutions")
public class institutionController {

    @Autowired
    public InstitutionCourseService institutionCourseService;

    @GetMapping("/getAllInstitutes")
    @PreAuthorize("hasAnyRole('STUDENT','UNIVERSITY_ADMIN','COUNSELLOR','INSTITUTE_ADMIN')")
    public List<InstitutionResponseDTO> getAllInstitutes() {
        return institutionCourseService.getAllInstitutions();
    }

    @GetMapping("/filterInstitution")
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<List<InstituteFilterDTO>> getInstitutionsByCategoryAndProgram(
            @RequestParam CategoryType category,
            @RequestParam DegreeType program
            ){
        List<InstituteFilterDTO> institutions= institutionCourseService.getInstitutionsByCategoryAndProgram(category, program);
        return ResponseEntity.ok(institutions);
    }

}
