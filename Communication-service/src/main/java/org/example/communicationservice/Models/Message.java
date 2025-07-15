package org.example.communicationservice.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.example.communicationservice.Enum.MessageStatus;
import org.example.communicationservice.Enum.MessageType;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "messages")
@Data
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Email(message = "Invalid recipient email format.")
    @Size(max = 100, message = "Recipient email must not exceed 100 characters.")
    private String recipientEmail;

    @Pattern(regexp = "^[0-9]{10}$", message = "Recipient phone must be a valid 10-digit number.")
    private String recipientPhone;

    @NotNull(message = "Message type is required.")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MessageType type; // EMAIL, SMS, PUSH

    @ManyToOne
    @JoinColumn(name = "template_id")
    private MessageTemplate template; // Optional linkage to template

    @Size(max = 255, message = "Subject must not exceed 255 characters.")
    private String subject;

    @NotNull(message = "Message status is required.")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MessageStatus status; // SENT, FAILED, PENDING

    private LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime sentAt;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public @Email(message = "Invalid recipient email format.") @Size(max = 100, message = "Recipient email must not exceed 100 characters.") String getRecipientEmail() {
        return recipientEmail;
    }

    public void setRecipientEmail(@Email(message = "Invalid recipient email format.") @Size(max = 100, message = "Recipient email must not exceed 100 characters.") String recipientEmail) {
        this.recipientEmail = recipientEmail;
    }

    public @Pattern(regexp = "^[0-9]{10}$", message = "Recipient phone must be a valid 10-digit number.") String getRecipientPhone() {
        return recipientPhone;
    }

    public void setRecipientPhone(@Pattern(regexp = "^[0-9]{10}$", message = "Recipient phone must be a valid 10-digit number.") String recipientPhone) {
        this.recipientPhone = recipientPhone;
    }

    public @NotNull(message = "Message type is required.") MessageType getType() {
        return type;
    }

    public void setType(@NotNull(message = "Message type is required.") MessageType type) {
        this.type = type;
    }

    public MessageTemplate getTemplate() {
        return template;
    }

    public void setTemplate(MessageTemplate template) {
        this.template = template;
    }

    public @Size(max = 255, message = "Subject must not exceed 255 characters.") String getSubject() {
        return subject;
    }

    public void setSubject(@Size(max = 255, message = "Subject must not exceed 255 characters.") String subject) {
        this.subject = subject;
    }

    public @NotNull(message = "Message status is required.") MessageStatus getStatus() {
        return status;
    }

    public void setStatus(@NotNull(message = "Message status is required.") MessageStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getSentAt() {
        return sentAt;
    }

    public void setSentAt(LocalDateTime sentAt) {
        this.sentAt = sentAt;
    }
}
