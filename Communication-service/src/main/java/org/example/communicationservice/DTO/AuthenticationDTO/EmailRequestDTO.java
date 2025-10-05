package org.example.communicationservice.DTO.AuthenticationDTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Map;

public class EmailRequestDTO {
    @NotBlank(message = "Recipient Mail is required")
    @Email(message = "Invalid email format")
    private String recipientEmail;

    @NotBlank(message = "Template name is required.")
    private String templateName;

    @NotNull(message = "Template variables must be provided.")
    @Size(min = 1, message = "At least one variable is required.")
    private Map<
                @NotBlank(message = "Variables key can't be blank.") String,
                @NotBlank(message = "Variable values cannot be blank") String
                > variables;

    public @NotBlank(message = "Recipient Mail is required") @Email(message = "Invalid email format") String getRecipientEmail() {
        return recipientEmail;
    }

    public void setRecipientEmail(@NotBlank(message = "Recipient Mail is required") @Email(message = "Invalid email format") String recipientEmail) {
        this.recipientEmail = recipientEmail;
    }

    public @NotBlank(message = "Template name is required.") String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(@NotBlank(message = "Template name is required.") String templateName) {
        this.templateName = templateName;
    }

    public @NotNull(message = "Template variables must be provided.") @Size(min = 1, message = "At least one variable is required.") Map<@NotBlank(message = "Variables key can't be blank.") String, @NotBlank(message = "Variable values cannot be blank") String> getVariables() {
        return variables;
    }

    public void setVariables(@NotNull(message = "Template variables must be provided.") @Size(min = 1, message = "At least one variable is required.") Map<@NotBlank(message = "Variables key can't be blank.") String, @NotBlank(message = "Variable values cannot be blank") String> variables) {
        this.variables = variables;
    }
}
