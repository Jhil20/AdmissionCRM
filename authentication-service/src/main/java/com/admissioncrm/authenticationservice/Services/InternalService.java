package com.admissioncrm.authenticationservice.Services;

import com.admissioncrm.authenticationservice.DTO.InternalDTO.CounsellorResponse;
import com.admissioncrm.authenticationservice.DTO.InternalDTO.CourseResponse;
import com.admissioncrm.authenticationservice.DTO.InternalDTO.InstituteResponse;
import com.admissioncrm.authenticationservice.DTO.InternalDTO.UserResponse;
import com.admissioncrm.authenticationservice.Entities.CoreEntities.User;
import com.admissioncrm.authenticationservice.Entities.Course;
import com.admissioncrm.authenticationservice.Entities.Institution;
import com.admissioncrm.authenticationservice.ExceptionHandling.ResourceNotFoundException;
import com.admissioncrm.authenticationservice.Repositories.CounsellorRepository;
import com.admissioncrm.authenticationservice.Repositories.CourseRepository;
import com.admissioncrm.authenticationservice.Repositories.InstitutionRepository;
import com.admissioncrm.authenticationservice.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InternalService {
    @Autowired
    InstitutionRepository institutionRepository;
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CounsellorRepository counsellorRepository;

    public CourseResponse getCourseFromName(String courseName)
    {
        try {
            Optional<Course> course = courseRepository.findByName(courseName);
            if (course.isEmpty())
            {
                throw new ResourceNotFoundException("Can't find course " + courseName);
            }
            return CourseResponse
                    .builder()
                    .id(course.get().getId())
                    .name(course.get().getName())
                    .fee(course.get().getFee())
                    .courseCode(course.get().getCourseCode())
                    .description(course.get().getDescription())
                    .years(course.get().getYears())
                    .semester(course.get().getSemester())
                    .degreeType(course.get().getDegreeType())
                    .eligibilityCriteria(course.get().getEligibilityCriteria())
                    .totalSeatsAcpc(course.get().getTotalSeatsAcpc())
                    .totalSeatsManagement(course.get().getTotalSeatsManagement())
                    .totalSeatsNri(course.get().getTotalSeatsNri())
                    .availableSeatsAcpc(course.get().getAvailableSeatsAcpc())
                    .availableSeatsManagement(course.get().getAvailableSeatsManagement())
                    .availableSeatsNri(course.get().getAvailableSeatsNri())
                    .fee(course.get().getFee())
                    .admissionStatus(course.get().getAdmissionStatus())
                    .build();
        }
        catch(DataAccessException e)
        {
            throw new RuntimeException("Error getting institute from DB");
        }
        catch (RuntimeException e)
        {
            throw new RuntimeException("Unexpected error in fetching the data");
        }
    }

    public InstituteResponse getInstituteFromName(String InstituteName)
    {
        try {
            Optional<Institution> institute = institutionRepository.findByInstituteName(InstituteName);
            if (institute.isEmpty())
            {
                throw new ResourceNotFoundException("Can't find Institute " + InstituteName);
            }
            return InstituteResponse.builder()
                    .id(institute.get().getId())
                    .instituteName(institute.get().getInstituteName())
                    .address(institute.get().getAddress())
                    .city(institute.get().getCity())
                    .state(institute.get().getState())
                    .pincode(institute.get().getPincode())
                    .institutionCode(institute.get().getInstitutionCode())
                    .type(institute.get().getType())
                    .description(institute.get().getDescription())
                    .contactEmail(institute.get().getContactEmail())
                    .contactNumber(institute.get().getContactNumber())
                    .establishedYear(institute.get().getEstablishedYear())
                    .principalName(institute.get().getPrincipalName())
                    .maxLeadsAllowed(institute.get().getMaxLeadsAllowed())
                    .isActive(institute.get().getActive())
                    .logoUrl(institute.get().getLogoUrl())
                    .websiteUrl(institute.get().getWebsiteUrl())
                    .tagline(institute.get().getTagline())
                    .facebookLink(institute.get().getFacebookLink())
                    .instagramLink(institute.get().getInstagramLink())
                    .linkedinLink(institute.get().getLinkedinLink())
                    .build();

        }
        catch(DataAccessException e)
        {
            throw new RuntimeException("Error getting institute from DB");
        }
        catch (RuntimeException e)
        {
            throw new RuntimeException("Unexpected error in fetching the data");
        }
    }

    public UserResponse getUserFromId(String userId)
    {
        Optional<User> existedUser = userRepository.findById(userId);
        if(existedUser.isEmpty())
        {
            throw new ResourceNotFoundException("Can't find user " + userId);
        }
        UserResponse user = new UserResponse();
        user.setId(existedUser.get().getId());
        user.setFirstName(existedUser.get().getFirstName());
        user.setLastName(existedUser.get().getLastName());
        user.setEmail(existedUser.get().getEmail());
        user.setRole(existedUser.get().getRole());
        user.setMobileNumber(existedUser.get().getMobileNumber());
        user.setUsername(existedUser.get().getUsername());

        return user;
    }

    public List<CounsellorResponse> getCounsellorsFromInstitute(String InstituteId)
    {
        List<CounsellorResponse> counsellors = counsellorRepository.findCounsellorsByInstituteId(InstituteId);
        if(counsellors.isEmpty())
        {
            throw new ResourceNotFoundException("Invalid Institutre Id");
        }
        return counsellors;
    }
}
