package org.example.communicationservice.Services.CommunicationServices;

import org.example.communicationservice.DTO.CommunicationRequest.TemplateRegisterRequest;
import org.example.communicationservice.Models.MessageTemplate;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface TemplateService {
    MessageTemplate createTemplate(TemplateRegisterRequest template);

    /**
     * Update an existing message template.
     */
    MessageTemplate updateTemplate(Long templateId, MessageTemplate updatedTemplate);

    /**
     * Retrieve a template by its unique name.
     */
    Optional<MessageTemplate> getTemplateByName(String name);

    /**
     * Retrieve all available templates.
     */
    List<MessageTemplate> getAllTemplates();

    /**
     * Delete a template by ID.
     */
    void deleteTemplate(Long templateId);

    String renderTemplate(String templateContent, Map<String, String> variables);

}
