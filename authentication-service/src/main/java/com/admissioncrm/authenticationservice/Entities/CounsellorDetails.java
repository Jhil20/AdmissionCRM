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
    @Column(name = "councellorId")
    private String id; // Same as User ID (UUID)+

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;

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
    @JoinColumn(name = "InstituteId",referencedColumnName = "counsellorId")
    private Institution institutionId;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

}
