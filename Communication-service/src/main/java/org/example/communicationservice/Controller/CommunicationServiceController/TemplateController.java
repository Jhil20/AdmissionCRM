package org.example.communicationservice.Controller.CommunicationServiceController;


import org.example.communicationservice.DTO.CommunicationRequest.TemplateRegisterRequest;
import org.example.communicationservice.Services.CommunicationServices.TemplateService;
import org.example.communicationservice.Utils.SuccessResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/MessageTemplate")
public class TemplateController {

    @Autowired
    TemplateService templateService;


    @PreAuthorize("hasRole('UNIVERSITY_ADMIN')")
    @PostMapping("/add")
    public ResponseEntity<SuccessResponse> addMessageTemplate(@Valid @RequestBody TemplateRegisterRequest templateRegisterRequest) {
        System.out.print("in controller");
        System.out.println(templateRegisterRequest);
        templateService.createTemplate(templateRegisterRequest);
        SuccessResponse successResponse = new SuccessResponse(
                LocalDateTime.now(),
                "Template added Successfully",
                HttpStatus.OK.value()
        );
        System.out.print("in controller again");
        return new ResponseEntity<>(successResponse, HttpStatus.OK);
    }
}
