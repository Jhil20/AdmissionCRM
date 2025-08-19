package com.admission_crm.lead_management.Controller;

import com.admission_crm.lead_management.DTO.LeadRequest;
import com.admission_crm.lead_management.Entity.LeadManagement.Lead;
import com.admission_crm.lead_management.Service.LeadService;
import com.admission_crm.lead_management.Utils.CustomUserDetails;
import com.admission_crm.lead_management.Utils.ErrorResponse;
import com.admission_crm.lead_management.Utils.SuccessResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/leads")
@RequiredArgsConstructor
public class LeadController {

    @Autowired
    private LeadService leadService;

    @PostMapping("/create")
    public ResponseEntity<?> createLead(@Valid @RequestBody LeadRequest leadRequest) {
        try {
            CustomUserDetails principle = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String userId = principle.getUsername();
            leadRequest.setUserId(userId);
            //Fetch instituteId from the name when APi is ready
            String instituteId = null;
            leadService.insertLead(leadRequest,instituteId);
            SuccessResponse response = new SuccessResponse(
                    LocalDateTime.now(),
                    "Successfully registered the query",
                    HttpStatus.OK.value()
            );
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                    HttpStatus.OK.value(),
                    "Error inserting the Query"
            );
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }
    }

     
}
