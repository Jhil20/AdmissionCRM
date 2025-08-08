package com.admissioncrm.authenticationservice.Entities;


import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "institutions")
public class Institution {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    // Basic Identity
    @Column(name = "institution_code", unique = true, nullable = false, length = 50)
    private String institutionCode;

    @Column(name = "name", nullable = false, length = 150)
    private String name;

    @Column(name = "type", length = 50)
    private String type; // e.g., "Engineering College", "Medical Institute", etc.

    @Column(name = "description", length = 500)
    private String description;

    // Location Details
    @Column(name = "address", length = 255)
    private String address;

    @Column(name = "city", length = 50)
    private String city;

    @Column(name = "state", length = 50)
    private String state;

    @Column(name = "pincode", length = 10)
    private String pincode;

    // Contact Info
    @Column(name = "contact_email", length = 100)
    private String contactEmail;

    @Column(name = "contact_number", length = 15)
    private String contactNumber;

    @Column(name = "established_year")
    private Integer establishedYear;

    @Column(name = "principal_name", length = 100)
    private String principalName;

    // Operational Configuration
    @Column(name = "max_leads_allowed")
    private Integer maxLeadsAllowed = 5000;

    @Column(name = "is_active")
    private Boolean isActive = true;

    // Branding
    @Column(name = "logo_url", length = 255)
    private String logoUrl;

    @Column(name = "website_url", length = 255)
    private String websiteUrl;

    @Column(name = "tagline", length = 150)
    private String tagline;

    @Column(name = "facebook_link", length = 255)
    private String facebookLink;

    @Column(name = "instagram_link", length = 255)
    private String instagramLink;

    @Column(name = "linkedin_link", length = 255)
    private String linkedinLink;

    @OneToMany(mappedBy = "institutionId",cascade = CascadeType.ALL)
    private List<Course> coursesId;

    @OneToMany(mappedBy = "institutionId",cascade = CascadeType.ALL)
    private List<CounsellorDetails> counsellorId;

    @OneToOne(mappedBy = "institutionId")
    private InstituteAdminDetails institutionAdminId;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

}
