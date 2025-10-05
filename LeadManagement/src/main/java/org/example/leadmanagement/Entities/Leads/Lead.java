package org.example.leadmanagement.Entities.Leads;

import jakarta.persistence.*;
import lombok.*;
import org.example.leadmanagement.Enum.ConversionStatus;
import org.example.leadmanagement.Enum.LeadStatus;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

@Entity
@Table(name = "leads")
@Getter
@Setter
@AllArgsConstructor
@Builder
public class Lead {
    private static final AtomicInteger counter = new AtomicInteger(0);

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private String id;

    // Unique tracking reference (auto-generated in service)
    @Column(name = "reference_id", unique = true, nullable = false, length = 50,updatable = false)
    private String referenceId;

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "leadTrackingSeqGen")
    @SequenceGenerator(name = "leadTrackingSeqGen", sequenceName = "lead_tracking_seq", allocationSize = 1)
    @Transient
    private Long trackingSeq;

    // Enum for lead status
    @Enumerated(EnumType.STRING)
    @Column(name = "lead_status", nullable = false, length = 30)
    private LeadStatus leadStatus;


    @Column(name = "assigned_to", nullable = true)
    private Long assignedTo; // counsellorId (from another service)

    @Column(name = "lead_remark", length = 500)
    private String leadRemark;

    @Column(name = "user_id", nullable = false)
    private String userId; // FK from user service

    // Enum for conversion status
    @Enumerated(EnumType.STRING)
    @Column(name = "conversion_status", length = 30)
    private ConversionStatus conversionStatus;

    @Column(name = "follow_up_date")
    private LocalDate followUpDate;

    // timestamps
    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    // Mapping to Admission Inquiry
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admissionInquiry", nullable = false,
            foreignKey = @ForeignKey(name = "fk_lead_inquiry"))
    private AdmissionInquiry admissionInquiry;

    // Mapping to Query (nullable)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "query_id", nullable = true,
            foreignKey = @ForeignKey(name = "fk_lead_query"))
    private Query query;

    @PrePersist
    public void prePersist() {
        // build tracking number only once
        if (this.referenceId == null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
            String timestamp = LocalDateTime.now().format(formatter);

            int seq = counter.getAndIncrement() % 1000000; // keeps 6 digits
            String seqStr = String.format("%06d", seq);
            this.referenceId = "LEAD-" + timestamp + "-" + seqStr;
        }
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }

    public Long getTrackingSeq() {
        return trackingSeq;
    }

    public void setTrackingSeq(Long trackingSeq) {
        this.trackingSeq = trackingSeq;
    }

    public LeadStatus getLeadStatus() {
        return leadStatus;
    }

    public void setLeadStatus(LeadStatus leadStatus) {
        this.leadStatus = leadStatus;
    }

    public Long getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(Long assignedTo) {
        this.assignedTo = assignedTo;
    }

    public String getLeadRemark() {
        return leadRemark;
    }

    public void setLeadRemark(String leadRemark) {
        this.leadRemark = leadRemark;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public ConversionStatus getConversionStatus() {
        return conversionStatus;
    }

    public void setConversionStatus(ConversionStatus conversionStatus) {
        this.conversionStatus = conversionStatus;
    }

    public LocalDate getFollowUpDate() {
        return followUpDate;
    }

    public void setFollowUpDate(LocalDate followUpDate) {
        this.followUpDate = followUpDate;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public AdmissionInquiry getAdmissionInquiry() {
        return admissionInquiry;
    }

    public void setAdmissionInquiry(AdmissionInquiry admissionInquiry) {
        this.admissionInquiry = admissionInquiry;
    }

    public Query getQuery() {
        return query;
    }

    public void setQuery(Query query) {
        this.query = query;
    }

    public Lead ()
    {

    }
}
