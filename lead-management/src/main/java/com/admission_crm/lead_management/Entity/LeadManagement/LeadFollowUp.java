package com.admission_crm.lead_management.Entity.LeadManagement;


import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "LeadFollowUp")
public class LeadFollowUp {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String leadId;

    private String counselorId;

    @Enumerated(EnumType.STRING)
    private LeadStatus status = LeadStatus.NEW;

    @Enumerated(EnumType.STRING)
    private FollowUpType followUpType;

    private LocalDateTime followUpDate;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
