package com.admissioncrm.authenticationservice.Entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "course_code", unique = true, nullable = false, length = 50)
    private String courseCode;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "description", length = 500)
    private String description;

    @Column(name = "duration_months")
    private Integer durationMonths;

    @Column(name = "level", length = 50)
    private String level; // e.g., Undergraduate, Postgraduate, Diploma

    @Column(name = "fee", precision = 10, scale = 2)
    private BigDecimal fee;

    @Column(name = "intake_capacity")
    private Integer intakeCapacity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "institutionId", nullable = false,referencedColumnName = "id")
    private Institution institutionId;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
