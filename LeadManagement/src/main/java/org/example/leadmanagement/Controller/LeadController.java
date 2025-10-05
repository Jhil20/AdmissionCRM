package org.example.leadmanagement.Controller;


import jakarta.servlet.http.HttpServletRequest;
import org.example.leadmanagement.DTO.Lead.Request.LeadCreationReq;
import org.example.leadmanagement.DTO.Lead.Response.LeadCreationRes;
import org.example.leadmanagement.Exception.LeadException;
import org.example.leadmanagement.Service.LeadService;
import org.example.leadmanagement.Utils.ErrorResponse;
import org.example.leadmanagement.Utils.SuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Map;

@RequestMapping("/api/lead")
@RestController
public class LeadController {
    @Autowired
    LeadService leadService;

    @PostMapping("/create")
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<?> createLead(@RequestBody LeadCreationReq leadCreationReq, HttpServletRequest request) {
        try {
            String userId = (String) request.getAttribute("userId");
            LeadCreationRes response = leadService.createLead(leadCreationReq,userId);
            SuccessResponse successResponse = new SuccessResponse(
                    LocalDateTime.now(),
                    HttpStatus.OK.value(),
                    "Lead created successfully",
                    Map.of("Lead",response)
            );
            return new ResponseEntity<>(successResponse, HttpStatus.OK);
        }
        catch(LeadException e){
            ErrorResponse error = new ErrorResponse(
                    LocalDateTime.now(),
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    e.getMessage()
            );
            return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
