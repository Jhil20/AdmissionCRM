package com.admission_crm.lead_management.Entity.LeadManagement;

import com.admission_crm.lead_management.Entity.Academic.Course;
import com.admission_crm.lead_management.Entity.Application.Application;
import com.admission_crm.lead_management.Entity.Communication.Communication;
import com.admission_crm.lead_management.Entity.CoreEntities.Institution;
import com.admission_crm.lead_management.Entity.CoreEntities.User;
import com.admission_crm.lead_management.Entity.FollowUp.LeadFollowUp;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "leads")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Lead {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String userId;

    private String institutionId;

    @Column(length = 100)
    private String qualification;

    private String courseInterest;

    @Column(name = "budget_range", length = 50)
    private String budgetRange;

    @Column(name = "Subject")
    private String subject;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    private LeadStatus status;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}