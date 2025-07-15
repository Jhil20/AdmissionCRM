package org.example.communicationservice.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.UUID;

import org.example.communicationservice.Enum.MessageType;

@Entity
@Table(name = "message_templates")
@Data
public class MessageTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank(message = "Template name is required.")
    @Size(min = 3, max = 100, message = "Name must be between 3 and 100 characters.")
    @Column(nullable = false, unique = true)
    private String name; // e.g., OTP_EMAIL

    @NotNull(message = "Message type is required.")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MessageType type;

    @Size(max = 255, message = "Subject must not exceed 255 characters.")
    private String subject;

    @NotBlank(message = "Content cannot be empty.")
    @Lob
    @Column(nullable = false,columnDefinition = "TEXT")
    private String content;

    private LocalDateTime createdAt = LocalDateTime.now();

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public @NotBlank(message = "Template name is required.") @Size(min = 3, max = 100, message = "Name must be between 3 and 100 characters.") String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "Template name is required.") @Size(min = 3, max = 100, message = "Name must be between 3 and 100 characters.") String name) {
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

    public @NotBlank(message = "Content cannot be empty.") String getContent() {
        return content;
    }

    public void setContent(@NotBlank(message = "Content cannot be empty.") String content) {
        this.content = content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public MessageTemplate(UUID id, String name, MessageType type, String subject, String content, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.subject = subject;
        this.content = content;
        this.createdAt = createdAt;
    }

    public MessageTemplate() {
    }

    @Override
    public String toString() {
        return "MessageTemplate{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", subject='" + subject + '\'' +
                ", content='" + content + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
