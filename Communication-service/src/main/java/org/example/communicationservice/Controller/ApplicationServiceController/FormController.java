package org.example.communicationservice.Controller.ApplicationServiceController;

import org.example.communicationservice.DTO.ApplicationRequest.FormSubmission;
import org.example.communicationservice.Services.ApplicationService.FormService;
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
@RequestMapping("/applicationForm")
public class FormController {

    @Autowired
    FormService formService;

    @PreAuthorize("hasAnyRole('STUDENT')")
    @PostMapping("/onSubmission")
    public ResponseEntity<SuccessResponse> onSubmission(@Valid @RequestBody FormSubmission formSubmission) {
        formService.FormSubmission(formSubmission);
        SuccessResponse response = new SuccessResponse(
                LocalDateTime.now(),
                "Email sent successfully",
                HttpStatus.OK.value()
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
