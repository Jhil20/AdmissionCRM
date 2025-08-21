package com.admissioncrm.authenticationservice.Entities;

import com.admissioncrm.authenticationservice.Entities.CoreEntities.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "counsellors")
public class CounsellorDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "councellorId")
    private String id; // Same as User ID (UUID)+

    @Column(name = "specialization", length = 100)
    private String specialization;

    @Column(name = "bio", length = 500)
    private String bio;

    @Column(name = "max_queries_handling")
    private Integer maxQueriesHandling = 100;

    @Column(name = "current_queries_count")
    private Integer currentQueriesCount = 0;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userId",referencedColumnName = "id")
    private User userId;

    @ManyToOne
    @JoinColumn(name = "InstituteId",referencedColumnName = "id")
    private Institution institutionId;

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

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public Integer getMaxQueriesHandling() {
        return maxQueriesHandling;
    }

    public void setMaxQueriesHandling(Integer maxQueriesHandling) {
        this.maxQueriesHandling = maxQueriesHandling;
    }

    public Integer getCurrentQueriesCount() {
        return currentQueriesCount;
    }

    public void setCurrentQueriesCount(Integer currentQueriesCount) {
        this.currentQueriesCount = currentQueriesCount;
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
