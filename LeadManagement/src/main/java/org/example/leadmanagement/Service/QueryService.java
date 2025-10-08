package org.example.leadmanagement.Service;

import jakarta.transaction.Transactional;
import org.example.leadmanagement.Client.AuthenticationServiceClient;
import org.example.leadmanagement.DTO.InternalDTO.UserResponse;
import org.example.leadmanagement.DTO.Query.Request.QueryCreateDTOReq;
import org.example.leadmanagement.Entities.Leads.AdmissionInquiry;
import org.example.leadmanagement.Entities.Leads.Query;
import org.example.leadmanagement.Exception.AdmissionInquiryException;
import org.example.leadmanagement.Exception.ResourceNotFound;
import org.example.leadmanagement.Repository.AdmissionInquiryRepo;
import org.example.leadmanagement.Repository.QueryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QueryService {

    @Autowired
    private AdmissionInquiryRepo admissionInquiryRepo;
    @Autowired
    private QueryRepo queryRepo;
    @Autowired
    private AuthenticationServiceClient authenticationServiceClient;

    @Transactional
    public String createQuery(QueryCreateDTOReq queryCreateDTOReq,String userId,String token)
    {
        try {
            UserResponse user = authenticationServiceClient.fetchUser(userId,token);
            if(user==null)
            {
                throw new ResourceNotFound(
                        "No User exist"
                );
            }
            System.out.print(user.getMobileNumber());
            System.out.print(queryCreateDTOReq.getInstituteId());
            if(queryCreateDTOReq.getInstituteId() == null)
            {
                queryCreateDTOReq.setInstituteId(authenticationServiceClient.fetchInstitute(queryCreateDTOReq.getInstituteName(),token).getId());
                System.out.print(queryCreateDTOReq.getInstituteId());
            }
            Optional<AdmissionInquiry> existedInquiry =  admissionInquiryRepo.findByUserIdAndInstituteId(userId,queryCreateDTOReq.getInstituteId());
            if(existedInquiry.isEmpty())
            {
                throw new AdmissionInquiryException("Admission Inquiry not completed. Please complete it first");
            }
            if (queryCreateDTOReq.getAdmissionInquiryId() == null)
            {
                queryCreateDTOReq.setAdmissionInquiryId(existedInquiry.get().getId());
            }

            Query query = new Query();
            query.setQuery(queryCreateDTOReq.getQuery());
            query.setInstituteId(queryCreateDTOReq.getInstituteId());
            query.setQuerySubject(queryCreateDTOReq.getQuerySubject());
            query.setInstituteName(queryCreateDTOReq.getInstituteName());
            query.setAdmissionInquiry(existedInquiry.get());

            existedInquiry.get().getQueries().add(query);

            admissionInquiryRepo.save(existedInquiry.get());
            return queryRepo.save(query).getId();
        } catch (ResourceNotFound | AdmissionInquiryException e) {
            throw e;
        }
        catch (DataAccessException e)
        {
            throw new RuntimeException("Error Creating the Query");
        }
        catch (RuntimeException e)
        {
            throw new RuntimeException("Unexpected Error Creating the Query");
        }
    }
}
