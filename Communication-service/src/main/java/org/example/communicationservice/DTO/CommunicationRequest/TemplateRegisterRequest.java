package org.example.communicationservice.DTO.CommunicationRequest;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.example.communicationservice.Enum.MessageType;

@Data
public class TemplateRegisterRequest {
    @NotBlank(message = "Template name is required.")
    private String name;

    @NotNull(message = "Message type is required.")
    private MessageType type;

    @Size(max = 255, message = "Subject must not exceed 255 characters.")
    private String subject;

    @NotBlank(message = "Template content cannot be blank.")
    private String content; // Rich HTML body (supports inline styling)


    public @NotBlank(message = "Template name is required.") String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "Template name is required.") String name) {
        this.name = name;
    }

    public @NotNull(message = "Message type is required.") MessageType getType() {
        return type;
    }

    public void setType(@NotNull(message = "Message type is required.") MessageType type) {
        this.type = type;
    }

    public @Size(max = 255, message = "Subject must not exceed 255 characters.") String getSubject() {
        return subject;
    }

    public void setSubject(@Size(max = 255, message = "Subject must not exceed 255 characters.") String subject) {
        this.subject = subject;
    }

    public @NotBlank(message = "Template content cannot be blank.") String getContent() {
        return content;
    }

    public void setContent(@NotBlank(message = "Template content cannot be blank.") String content) {
        this.content = content;
    }

    public TemplateRegisterRequest(String name, MessageType type, String subject, String content) {
        this.name = name;
        this.type = type;
        this.subject = subject;
        this.content = content;
    }

    @Override
    public String toString() {
        return "TemplateRegisterRequest{" +
                "name='" + name + '\'' +
                ", type=" + type +
                ", subject='" + subject + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
