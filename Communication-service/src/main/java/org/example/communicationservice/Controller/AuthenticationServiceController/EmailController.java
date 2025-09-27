package org.example.communicationservice.Controller.AuthenticationServiceController;

import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import org.example.communicationservice.DTO.AuthenticationDTO.EmailRequestDTO;
import org.example.communicationservice.Services.AuthenticationService.EmailService;
import org.example.communicationservice.Utils.SuccessResponse;
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
@RequestMapping("/email")
public class EmailController {

    @Autowired
    private EmailService emailService;


    @PostMapping("/onRegister")
    public ResponseEntity onRegister(@Valid @RequestBody EmailRequestDTO emailRequestDTO) throws MessagingException {
        System.out.println("Received email request: " + emailRequestDTO);
        emailService.registerStudent(emailRequestDTO);
        SuccessResponse response = new SuccessResponse(
                LocalDateTime.now(),
                "Email sent successfully",
                HttpStatus.OK.value()
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
