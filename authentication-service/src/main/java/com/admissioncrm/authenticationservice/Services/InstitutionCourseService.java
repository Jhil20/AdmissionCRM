package com.admissioncrm.authenticationservice.Services;

import com.admissioncrm.authenticationservice.DTO.Courses.CourseResponseDTO;
import com.admissioncrm.authenticationservice.DTO.Courses.InstitutionResponseDTO;
import com.admissioncrm.authenticationservice.Entities.Course;
import com.admissioncrm.authenticationservice.Entities.Institution;
import com.admissioncrm.authenticationservice.Repositories.CourseRepository;
import com.admissioncrm.authenticationservice.Repositories.InstitutionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class InstitutionCourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private InstitutionRepository institutionRepository;

    public List<InstitutionResponseDTO> getAllInstitutions() {
        List<Institution> institutions = institutionRepository.findAll();
        List<InstitutionResponseDTO> institutionResponseList = new ArrayList<>();
        for (Institution institution : institutions) {
            InstitutionResponseDTO dto=new InstitutionResponseDTO(
                    institution.getId(),
                    institution.getInstitutionCode(),
                    institution.getInstituteName(),
                    institution.getInstituteFullform(),
                    institution.getType(),
                    institution.getDescription(),
                    institution.getAddress(),
                    institution.getCity(),
                    institution.getState(),
                    institution.getPincode(),
                    institution.getContactEmail(),
                    institution.getContactNumber(),
                    institution.getEstablishedYear(),
                    institution.getPrincipalName(),
                    institution.getMaxLeadsAllowed(),
                    institution.getActive(),
                    institution.getLogoUrl(),
                    institution.getWebsiteUrl(),
                    institution.getTagline(),
                    institution.getFacebookLink(),
                    institution.getInstagramLink(),
                    institution.getLinkedinLink()
            );
            institutionResponseList.add(dto);
        }
        return institutionResponseList;

    }

    public List<CourseResponseDTO> getCoursesByInstitutions(String institutionId) {

//        Institution institution = institutionRepository.findById(institutionId).get();
//        return courseRepository.findAllByInstitutionId(institution);

        List<Course> courses= courseRepository.findCourseByInstitutionId_Id(institutionId);

        List<CourseResponseDTO> list = new ArrayList<>();
        for (Course course : courses) {
            CourseResponseDTO dto = new CourseResponseDTO(
                    course.getAvailableSeatsNri(),
                    course.getId(),
                    course.getName(),
                    course.getCourseCode(),
                    course.getDescription(),
                    course.getYears(),
                    course.getSemester(),
                    course.getDegreeType(),
                    course.getEligibilityCriteria(),
                    course.getTotalSeatsAcpc(),
                    course.getTotalSeatsManagement(),
                    course.getTotalSeatsNri(),
                    course.getAvailableSeatsAcpc(),
                    course.getAvailableSeatsManagement(),
                    course.getFee(),
                    course.getAdmissionStatus(),
                    course.getAdmissionStartDate(),
                    course.getAdmissionEndDate()
            );
            list.add(dto);
        }
        return list;
    }
}
