package com.admissioncrm.authenticationservice.Controllers;

import com.admissioncrm.authenticationservice.DTO.Courses.CourseResponseDTO;
import com.admissioncrm.authenticationservice.Entities.Course;

import com.admissioncrm.authenticationservice.Services.InstitutionCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class courseController {

    @Autowired
    private InstitutionCourseService institutionCourseService;

    @GetMapping("/institutions/{institutionId}")
     @PreAuthorize("hasAnyRole('STUDENT','UNIVERSITY_ADMIN','COUNSELLOR','INSTITUTE_ADMIN')")
    public List<CourseResponseDTO> getAllCoursesbyInstituiton(@PathVariable String institutionId) {
        return institutionCourseService.getCoursesByInstitutions(institutionId);
    }

}
