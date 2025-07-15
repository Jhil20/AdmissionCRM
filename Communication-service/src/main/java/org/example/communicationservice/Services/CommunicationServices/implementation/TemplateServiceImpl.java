package org.example.communicationservice.Services.CommunicationServices.implementation;

import org.example.communicationservice.DTO.CommunicationRequest.TemplateRegisterRequest;
import org.example.communicationservice.Models.MessageTemplate;
import org.example.communicationservice.Repository.MessageTemplateRepo;
import org.example.communicationservice.Services.CommunicationServices.TemplateService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.example.communicationservice.Enum.MessageType;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class TemplateServiceImpl implements TemplateService {

    @Autowired
    MessageTemplateRepo messageTemplateRepo;


    @Override
    @Transactional
    public MessageTemplate createTemplate(TemplateRegisterRequest template) {
        try {
            System.out.print("in service");
            MessageTemplate messageTemplate = new MessageTemplate();
            messageTemplate.setName(template.getName());
            messageTemplate.setContent(template.getContent());
            messageTemplate.setType(MessageType.EMAIL);
            messageTemplate.setSubject(template.getSubject());
            System.out.print(messageTemplate);
            return messageTemplateRepo.save(messageTemplate);
        }
        catch (Exception e) {
            throw new RuntimeException("Failed adding Template");
        }

    }

    @Override
    public MessageTemplate updateTemplate(Long templateId, MessageTemplate updatedTemplate) {
        return null;
    }

    @Override
    public Optional<MessageTemplate> getTemplateByName(String name) {
        return Optional.empty();
    }

    @Override
    public List<MessageTemplate> getAllTemplates() {
        return List.of();
    }

    @Override
    public void deleteTemplate(Long templateId) {

    }

    @Override
    public String renderTemplate(String templateContent, Map<String, String> variables) {
        for (Map.Entry<String, String> entry : variables.entrySet()) {
            templateContent = templateContent.replace("{{" + entry.getKey() + "}}", entry.getValue());
        }
        return templateContent;
    }
}
