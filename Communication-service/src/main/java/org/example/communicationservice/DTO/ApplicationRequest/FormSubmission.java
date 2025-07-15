package org.example.communicationservice.DTO.ApplicationRequest;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.Map;

@Data
public class FormSubmission {

    @NotBlank(message = "Recipient email is required.")
    @Email(message = "Invalid email format.")
    private String recipientEmail;

    @NotBlank(message = "Template name is required.")
    private String templateName;

    @NotNull(message = "Template variables must be provided.")
    @Size(min = 1, message = "At least one variable is required.")
    private Map<@NotBlank(message = "Variable keys cannot be blank.")
            String,
            @NotBlank(message = "Variable values cannot be blank.")
                    String> variables;

    public @NotBlank(message = "Recipient email is required.") @Email(message = "Invalid email format.") String getRecipientEmail() {
        return recipientEmail;
    }

    public void setRecipientEmail(@NotBlank(message = "Recipient email is required.") @Email(message = "Invalid email format.") String recipientEmail) {
        this.recipientEmail = recipientEmail;
    }

    public @NotBlank(message = "Template name is required.") String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(@NotBlank(message = "Template name is required.") String templateName) {
        this.templateName = templateName;
    }

    public @NotNull(message = "Template variables must be provided.") @Size(min = 1, message = "At least one variable is required.") Map<@NotBlank(message = "Variable keys cannot be blank.") String, @NotBlank(message = "Variable values cannot be blank.") String> getVariables() {
        return variables;
    }

    public void setVariables(@NotNull(message = "Template variables must be provided.") @Size(min = 1, message = "At least one variable is required.") Map<@NotBlank(message = "Variable keys cannot be blank.") String, @NotBlank(message = "Variable values cannot be blank.") String> variables) {
        this.variables = variables;
    }
}
