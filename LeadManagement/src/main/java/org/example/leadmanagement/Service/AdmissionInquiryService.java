package org.example.leadmanagement.Service;

import org.example.leadmanagement.Client.AuthenticationServiceClient;
import org.example.leadmanagement.DTO.AdmissionInquiry.Request.AdmissionInquiryCreateReq;
import org.example.leadmanagement.DTO.AdmissionInquiry.Response.AdmissionInquiryCreateRes;
import org.example.leadmanagement.DTO.AdmissionInquiry.Response.InstituteEnquiryDTO;
import org.example.leadmanagement.DTO.InternalDTO.UserResponse;
import org.example.leadmanagement.Entities.Leads.AdmissionInquiry;
import org.example.leadmanagement.Exception.AdmissionInquiryException;
import org.example.leadmanagement.Exception.AlreadyExistException;
import org.example.leadmanagement.Exception.ResourceNotFound;
import org.example.leadmanagement.Repository.AdmissionInquiryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AdmissionInquiryService {

    @Autowired
    AdmissionInquiryRepo admissionInquiryRepo;
    @Autowired
    AuthenticationServiceClient authenticationServiceClient;


    public AdmissionInquiryCreateRes create(AdmissionInquiryCreateReq admissionInquiryCreate, String userId, String token) {
        try {

            UserResponse user = authenticationServiceClient.fetchUser(userId,token);
            if(user==null)
            {
                throw new ResourceNotFound(
                        "No User exist"
                );
            }
            admissionInquiryCreate.setInstituteId(authenticationServiceClient.fetchInstitute(admissionInquiryCreate.getInstituteName(),token).getId());
            admissionInquiryCreate.setCourseId(authenticationServiceClient.fetchCourse(admissionInquiryCreate.getCourseName(),token).getId());

            System.out.print(admissionInquiryCreate.getInstituteId());
            System.out.print(admissionInquiryCreate.getCourseId());
            Optional<AdmissionInquiry> existedInquiry = admissionInquiryRepo.findByUserIdAndInstituteId(userId,admissionInquiryCreate.getInstituteId());
            if (existedInquiry.isPresent()) {
                throw new AlreadyExistException("Admission Inquiry Already Exists in this Institute");
            }


            AdmissionInquiry inquiry = new AdmissionInquiry(
                    admissionInquiryCreate.getFirstName(),
                    admissionInquiryCreate.getMiddleName(),
                    admissionInquiryCreate.getLastName(),
                    admissionInquiryCreate.getCity(),
                    admissionInquiryCreate.getState(),
                    admissionInquiryCreate.getCountry(),
                    admissionInquiryCreate.getGender(),
                    admissionInquiryCreate.getDob(),
                    admissionInquiryCreate.getGujcetMarks(),
                    admissionInquiryCreate.getGujcetPercentileRank(),
                    admissionInquiryCreate.getAcpcRank(),
                    admissionInquiryCreate.getTwelfthPercentileRank(),
                    admissionInquiryCreate.getProgram(),
                    admissionInquiryCreate.getInstituteId(),
                    admissionInquiryCreate.getInstituteName(),
                    admissionInquiryCreate.getCourseId(),
                    admissionInquiryCreate.getCourseName(),
                    admissionInquiryCreate.getCategory(),
                    userId
            );
            AdmissionInquiry savedInquiry = admissionInquiryRepo.save(inquiry);
            AdmissionInquiryCreateRes response = new AdmissionInquiryCreateRes();
            response.setAdmissionInquiryId(savedInquiry.getId());
            response.setInstituteName(savedInquiry.getInstituteName());
            response.setInstituteId(savedInquiry.getInstituteId());
            return response;

        } catch (AlreadyExistException | ResourceNotFound e) {
            throw e;
        }
        catch (DataAccessException e)
        {
            throw new AdmissionInquiryException("Database error while creating Admission Inquiry");
        }
        catch (RuntimeException e) {
            throw new AdmissionInquiryException("Unexpected error while Admission Inquiry");
        }
    }

    public boolean hasAdmissionEnquiry(String userId) {
        return admissionInquiryRepo.existsByUserId(userId);
    }

    public List<InstituteEnquiryDTO> getInstituteByUserId(String userId) {
        List<AdmissionInquiry> inquiryList = admissionInquiryRepo.findByUserId(userId);
        List<InstituteEnquiryDTO> dtoList = new ArrayList<>();

        for (AdmissionInquiry inquiry : inquiryList) {
            InstituteEnquiryDTO dto = new InstituteEnquiryDTO();
            dto.setInstituteId(inquiry.getInstituteId());
            dto.setInstituteName(inquiry.getInstituteName());

            dtoList.add(dto);
        }
        return dtoList;
    }

}
