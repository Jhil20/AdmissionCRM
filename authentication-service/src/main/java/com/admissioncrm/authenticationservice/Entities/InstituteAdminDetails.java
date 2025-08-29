package com.admissioncrm.authenticationservice.Entities;

import com.admissioncrm.authenticationservice.Entities.CoreEntities.User;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "institutional_admins")
public class    InstituteAdminDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id; // Same as User ID (UUID)

//    @OneToOne
//    @MapsId
//    @JoinColumn(name = "user_id")
//    private User user;

    @Column(name = "designation", length = 100)
    private String designation = "Admission Coordinator";

    // Contact
    @Column(name = "office_phone", length = 15)
    private String officePhone;

    @Column(name = "alternate_email", length = 100)
    private String alternateEmail;

    // Permissions & Limits
    @Column(name = "max_counsellors_allowed")
    private Integer maxCounsellorsAllowed = 20;

    @Column(name = "max_lead_pool_size")
    private Integer maxLeadPoolSize = 1000;

    // Work Monitoring
    @Column(name = "total_counsellors_managed")
    private Integer totalCounsellorsManaged = 0;

    @Column(name = "active_leads")
    private Integer activeLeads = 0;

    @Column(name = "resolved_queries_count")
    private Integer resolvedQueriesCount = 0;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userId",referencedColumnName = "id")
    private User userId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "institutionId",referencedColumnName = "id")
    private Institution institutionId;

    // Audit
    @Column(name = "last_activity_time")
    private LocalDateTime lastActivityTime;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getOfficePhone() {
        return officePhone;
    }

    public void setOfficePhone(String officePhone) {
        this.officePhone = officePhone;
    }

    public String getAlternateEmail() {
        return alternateEmail;
    }

    public void setAlternateEmail(String alternateEmail) {
        this.alternateEmail = alternateEmail;
    }

    public Integer getMaxCounsellorsAllowed() {
        return maxCounsellorsAllowed;
    }

    public void setMaxCounsellorsAllowed(Integer maxCounsellorsAllowed) {
        this.maxCounsellorsAllowed = maxCounsellorsAllowed;
    }

    public Integer getMaxLeadPoolSize() {
        return maxLeadPoolSize;
    }

    public void setMaxLeadPoolSize(Integer maxLeadPoolSize) {
        this.maxLeadPoolSize = maxLeadPoolSize;
    }

    public Integer getTotalCounsellorsManaged() {
        return totalCounsellorsManaged;
    }

    public void setTotalCounsellorsManaged(Integer totalCounsellorsManaged) {
        this.totalCounsellorsManaged = totalCounsellorsManaged;
    }

    public Integer getActiveLeads() {
        return activeLeads;
    }

    public void setActiveLeads(Integer activeLeads) {
        this.activeLeads = activeLeads;
    }

    public Integer getResolvedQueriesCount() {
        return resolvedQueriesCount;
    }

    public void setResolvedQueriesCount(Integer resolvedQueriesCount) {
        this.resolvedQueriesCount = resolvedQueriesCount;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public Institution getInstitutionId() {
        return institutionId;
    }

    public void setInstitutionId(Institution institutionId) {
        this.institutionId = institutionId;
    }

    public LocalDateTime getLastActivityTime() {
        return lastActivityTime;
    }

    public void setLastActivityTime(LocalDateTime lastActivityTime) {
        this.lastActivityTime = lastActivityTime;
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
}
