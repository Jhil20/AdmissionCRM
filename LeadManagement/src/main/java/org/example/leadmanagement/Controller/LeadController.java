package org.example.leadmanagement.Controller;


import jakarta.servlet.http.HttpServletRequest;
import org.example.leadmanagement.DTO.Lead.Request.LeadCreationReq;
import org.example.leadmanagement.DTO.Lead.Response.LeadCreationRes;
import org.example.leadmanagement.DTO.Lead.Response.ListLeadResponse;
import org.example.leadmanagement.Exception.LeadException;
import org.example.leadmanagement.Service.LeadService;
import org.example.leadmanagement.Utils.ErrorResponse;
import org.example.leadmanagement.Utils.SuccessResponse;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RequestMapping("/api/lead")
@RestController
public class LeadController {
    @Autowired
    LeadService leadService;

    @PostMapping("/create")
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<?> createLead(@RequestBody LeadCreationReq leadCreationReq, HttpServletRequest request,@RequestHeader("Authorization") String token) {
        try {
            String userId = (String) request.getAttribute("userId");
            LeadCreationRes response = leadService.createLead(leadCreationReq,userId,token);
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
        } catch (SchedulerException e) {
            throw new RuntimeException(e);
        }

    }

    @GetMapping("/student/list")
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<?> listAllLeads(HttpServletRequest request,@RequestHeader("Authorization") String token) {
        try{
            String userId = (String) request.getAttribute("userId");
            List<ListLeadResponse> leads = leadService.listLeads(userId,token);
            SuccessResponse successResponse = new SuccessResponse(
                    LocalDateTime.now(),
                    HttpStatus.OK.value(),
                    Map.of("Leads",leads)
            );
            return new ResponseEntity<>(successResponse, HttpStatus.OK);
        }
        catch(LeadException e)
        {
            ErrorResponse error = new ErrorResponse(
                    LocalDateTime.now(),
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    e.getMessage()
            );
            return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
