package org.example.communicationservice.Services.AuthenticationService;

import jakarta.mail.MessagingException;
import org.example.communicationservice.DTO.AuthenticationDTO.EmailRequestDTO;
import org.example.communicationservice.Enum.MessageStatus;
import org.example.communicationservice.Enum.MessageType;
import org.example.communicationservice.Exception.ResourceNotFound;
import org.example.communicationservice.Models.Message;
import org.example.communicationservice.Models.MessageTemplate;
import org.example.communicationservice.Repository.MessageRepo;
import org.example.communicationservice.Repository.MessageTemplateRepo;
import org.example.communicationservice.Services.CommunicationServices.TemplateService;
import org.example.communicationservice.Services.MessageSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class EmailService {

    @Autowired
    private MessageSender messageSender;
    @Autowired
    private MessageTemplateRepo messageTemplateRepo;
    @Autowired
    private MessageRepo messageRepo;
    @Autowired
    private TemplateService templateService;

    public void registerStudent(EmailRequestDTO emailRequestDTO) throws ResourceNotFound, MessagingException {

        System.out.println("==== Received EmailRequestDTO ====");
        System.out.println("Recipient: " + emailRequestDTO.getRecipientEmail());
        System.out.println("Template Name: " + emailRequestDTO.getTemplateName());
        System.out.println("Variables: " + emailRequestDTO.getVariables());
        System.out.println("=================================");
        Map<String,String> userInfo = emailRequestDTO.getVariables();

        if(!userInfo.containsKey("firstName"))
        {
            throw new ResourceNotFound("First Name Not Found");
        }
        if (!userInfo.containsKey("userName")) {
            throw new ResourceNotFound("Missing variable: userName");
        }
        if (!userInfo.containsKey("password")) {
            throw new ResourceNotFound("Missing variable: password");
        }

        String firstName = userInfo.get("firstName");
        String userName = userInfo.get("userName");
        String password = userInfo.get("password");
        String recipientEmail = emailRequestDTO.getRecipientEmail();
        String TemplateName = emailRequestDTO.getTemplateName();

        Optional<MessageTemplate> template= messageTemplateRepo.findByName(TemplateName);
        if(template.isPresent()) {
            String htmlContent = templateService.renderTemplate(template.get().getContent(), userInfo);
            try {


                System.out.println("Sending email to: " + recipientEmail);
                System.out.println("Subject: " + template.get().getSubject());
                System.out.println("HTML content: " + htmlContent);



                messageSender.sendMail(recipientEmail, template.get().getSubject(), htmlContent);
            }
            catch (MessagingException e) {
                System.err.println("Error sending email: " + e.getMessage());
                e.printStackTrace();
                throw e; // rethrow for upstream handling
            }
            catch (Exception e) {
                System.err.println("Unexpected error sending email: " + e.getMessage());
                e.printStackTrace();
                throw new RuntimeException("Failed to send email: " + e.getMessage());
            }

            Message message = new Message();
            message.setRecipientEmail(recipientEmail);
            message.setSubject(template.get().getSubject());
            message.setTemplate(template.get());
            message.setStatus(MessageStatus.SENT);
            message.setType(MessageType.EMAIL);


            messageRepo.save(message);
        }
        else
        {
            throw new ResourceNotFound("Resource not found");
        }
    }
}
