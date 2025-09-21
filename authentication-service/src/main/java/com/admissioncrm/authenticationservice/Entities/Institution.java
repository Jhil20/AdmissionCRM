package com.admissioncrm.authenticationservice.Entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @Column(name = "institute_name", nullable = false, length = 150)
    private String instituteName;

    @Column(name = "institute_fullform", length = 100)
    private String instituteFullform;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInstitutionCode() {
        return institutionCode;
    }

    public void setInstitutionCode(String institutionCode) {
        this.institutionCode = institutionCode;
    }

    public String getInstituteName() {
        return instituteName;
    }

    public void setInstituteName(String instituteName) {
        this.instituteName = instituteName;
    }

    public String getInstituteFullform() {
        return instituteFullform;
    }

    public void setInstituteFullform(String instituteFullform) {
        this.instituteFullform = instituteFullform;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public Integer getEstablishedYear() {
        return establishedYear;
    }

    public void setEstablishedYear(Integer establishedYear) {
        this.establishedYear = establishedYear;
    }

    public String getPrincipalName() {
        return principalName;
    }

    public void setPrincipalName(String principalName) {
        this.principalName = principalName;
    }

    public Integer getMaxLeadsAllowed() {
        return maxLeadsAllowed;
    }

    public void setMaxLeadsAllowed(Integer maxLeadsAllowed) {
        this.maxLeadsAllowed = maxLeadsAllowed;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getWebsiteUrl() {
        return websiteUrl;
    }

    public void setWebsiteUrl(String websiteUrl) {
        this.websiteUrl = websiteUrl;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public String getFacebookLink() {
        return facebookLink;
    }

    public void setFacebookLink(String facebookLink) {
        this.facebookLink = facebookLink;
    }

    public String getInstagramLink() {
        return instagramLink;
    }

    public void setInstagramLink(String instagramLink) {
        this.instagramLink = instagramLink;
    }

    public String getLinkedinLink() {
        return linkedinLink;
    }

    public void setLinkedinLink(String linkedinLink) {
        this.linkedinLink = linkedinLink;
    }

    public List<Course> getCoursesId() {
        return coursesId;
    }

    public void setCoursesId(List<Course> coursesId) {
        this.coursesId = coursesId;
    }

    public List<CounsellorDetails> getCounsellorId() {
        return counsellorId;
    }

    public void setCounsellorId(List<CounsellorDetails> counsellorId) {
        this.counsellorId = counsellorId;
    }

    public InstituteAdminDetails getInstitutionAdminId() {
        return institutionAdminId;
    }

    public void setInstitutionAdminId(InstituteAdminDetails institutionAdminId) {
        this.institutionAdminId = institutionAdminId;
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
