package org.example.leadmanagement.Controller;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.example.leadmanagement.DTO.AdmissionInquiry.Request.AdmissionInquiryCreateReq;
import org.example.leadmanagement.DTO.AdmissionInquiry.Response.AdmissionInquiryCreateRes;
import org.example.leadmanagement.DTO.AdmissionInquiry.Response.InstituteEnquiryDTO;
import org.example.leadmanagement.Exception.AdmissionInquiryException;
import org.example.leadmanagement.Service.AdmissionInquiryService;
import org.example.leadmanagement.Utils.ErrorResponse;
import org.example.leadmanagement.Utils.SuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/Admission-Inquiry")
public class AdmissioninquiryController {

    @Autowired
    private AdmissionInquiryService admissionInquiryService;

    @PostMapping("/create")
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<?> createAdmissionInquiry(@Valid @RequestBody AdmissionInquiryCreateReq admissionInquiryCreate, HttpServletRequest request, @RequestHeader("Authorization") String token) {
        try {
            token = token.startsWith("Bearer ") ? token.substring(7) : token;
            String userId = (String) request.getAttribute("userId");
            AdmissionInquiryCreateRes response = admissionInquiryService.create(admissionInquiryCreate,userId,token);
            SuccessResponse successResponse = new SuccessResponse(
                    LocalDateTime.now(),
                    HttpStatus.OK.value(),
                    Map.of("AdmissionInquiry" ,response)
            );
            return new ResponseEntity<>(successResponse, HttpStatus.OK);
        }
        catch (AdmissionInquiryException e) {
            ErrorResponse error = new ErrorResponse(
                    LocalDateTime.now(),
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    e.getMessage()
            );
            return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/checkEnquiry")
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<?> checkEnquiry(HttpServletRequest request) {
        String userId = (String) request.getAttribute("userId");
        boolean exists = admissionInquiryService.hasAdmissionEnquiry(userId);
        return new ResponseEntity<>(exists, HttpStatus.OK);
    }

    @GetMapping("/user-Institutes")
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<?> getUserInstitutes(HttpServletRequest request) {
        try{
            String userId = (String) request.getAttribute("userId");
            List<InstituteEnquiryDTO> institutes= admissionInquiryService.getInstituteByUserId(userId);
            return new ResponseEntity<>(institutes, HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
