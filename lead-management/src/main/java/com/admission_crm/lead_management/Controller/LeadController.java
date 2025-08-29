package com.admission_crm.lead_management.Controller;

import com.admission_crm.lead_management.DTO.LeadRequest;
import com.admission_crm.lead_management.Service.LeadService;
import com.admission_crm.lead_management.Utils.ErrorResponse;
import com.admission_crm.lead_management.Utils.SuccessResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<?> createLead(HttpServletRequest request, @Valid @RequestBody LeadRequest leadRequest) {
        try {
            String userId = (String) request.getAttribute("userId");
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

    @PostMapping("/assign-lead/{counsellorId}")
    @PreAuthorize("hasRole('INSTITUE_ADMIN')")
    public ResponseEntity<?> assignLeadToCounsellor(@PathVariable String counsellorId,@RequestParam String leadId ) {
        //verify the counsellor from the authentication service
        try{

        }
        catch(Exception e){

        }
    }
}
