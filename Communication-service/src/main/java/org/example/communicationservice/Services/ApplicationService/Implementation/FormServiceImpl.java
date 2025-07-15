package org.example.communicationservice.Services.ApplicationService.Implementation;

import org.example.communicationservice.DTO.ApplicationRequest.FormSubmission;
import org.example.communicationservice.Enum.MessageStatus;
import org.example.communicationservice.Enum.MessageType;
import org.example.communicationservice.Models.Message;
import org.example.communicationservice.Models.MessageTemplate;
import org.example.communicationservice.Repository.MessageRepo;
import org.example.communicationservice.Repository.MessageTemplateRepo;
import org.example.communicationservice.Services.ApplicationService.FormService;
import java.util.Map;
import java.util.Optional;

import org.example.communicationservice.Exception.ResourceNotFound;
import org.example.communicationservice.Services.CommunicationServices.TemplateService;
import org.example.communicationservice.Services.MessageSender;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FormServiceImpl implements FormService {

    @Autowired
    private MessageSender messageSender;
    @Autowired
    private MessageTemplateRepo messageTemplateRepo;
    @Autowired
    private MessageRepo messageRepo;
    @Autowired
    private TemplateService templateService;


    @Override
    @Transactional

    public void FormSubmission(FormSubmission formSubmission) throws ResourceNotFound {
        Map<String, String> userInfo = formSubmission.getVariables();
        if (!userInfo.containsKey("name")) {
            throw new ResourceNotFound("name is not there");
        }
        if (!userInfo.containsKey("applicationID")) {
            throw new ResourceNotFound("application ID is not there");
        }
        if (!userInfo.containsKey("status")) {
            throw new ResourceNotFound("status is not there");
        }
        if (!userInfo.containsKey("phoneNumber")) {
            throw new ResourceNotFound("phone number is not there");
        }
        String applicationID = userInfo.get("applicationID");
        String phoneNumber = userInfo.get("phoneNumber");
        String name = userInfo.get("name");
        String recipientEmail = formSubmission.getRecipientEmail();
        String TemplateName = formSubmission.getTemplateName();

        Optional<MessageTemplate> template = messageTemplateRepo.findByName(TemplateName);
        if (template.isPresent()) {
            String htmlContent = templateService.renderTemplate(template.get().getContent(),userInfo);
            try {
                messageSender.sendMail(recipientEmail,template.get().getSubject(),htmlContent);
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
            Message message = new Message();
            message.setRecipientEmail(recipientEmail);
            message.setRecipientPhone(phoneNumber);
            message.setSubject(template.get().getSubject());
            message.setStatus(MessageStatus.SENT);
            message.setType(MessageType.EMAIL);
            message.setTemplate(template.get());

            messageRepo.save(message);
        }
        else
        {
            throw new ResourceNotFound("Resource not found");
        }
    }


}
