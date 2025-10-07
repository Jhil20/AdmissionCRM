package com.admissioncrm.authenticationservice.DTO.Courses;

import com.admissioncrm.authenticationservice.Enums.CategoryType;
import com.admissioncrm.authenticationservice.Enums.Institute;

public class InstitutionResponseDTO {
    private String id;
    private String institutionCode;
    private String instituteName;
    private String instituteFullform;
    private CategoryType type;
    private String description;
    private String address;
    private String city;
    private String state;
    private String pincode;
    private String contactEmail;
    private String contactNumber;
    private Integer establishedYear;
    private String principalName;    private Integer maxLeadsAllowed;
    private Boolean isActive;
    private String logoUrl;
    private String websiteUrl;
    private String tagline;
    private String facebookLink;
    private String instagramLink;
    private String linkedinLink;

    public InstitutionResponseDTO() {
    }


    public InstitutionResponseDTO(String id, String institutionCode, String instituteName,String instituteFullform, CategoryType type,
                                  String description, String address, String city, String state, String pincode,
                                  String contactEmail, String contactNumber, Integer establishedYear,
                                  String principalName, Integer maxLeadsAllowed, Boolean isActive,
                                  String logoUrl, String websiteUrl, String tagline,
                                  String facebookLink, String instagramLink, String linkedinLink) {
        this.id = id;
        this.institutionCode = institutionCode;
        this.instituteName = instituteName;
        this.instituteFullform = instituteFullform;
        this.type = type;
        this.description = description;
        this.address = address;
        this.city = city;
        this.state = state;
        this.pincode = pincode;
        this.contactEmail = contactEmail;
        this.contactNumber = contactNumber;
        this.establishedYear = establishedYear;
        this.principalName = principalName;
        this.maxLeadsAllowed = maxLeadsAllowed;
        this.isActive = isActive;
        this.logoUrl = logoUrl;
        this.websiteUrl = websiteUrl;
        this.tagline = tagline;
        this.facebookLink = facebookLink;
        this.instagramLink = instagramLink;
        this.linkedinLink = linkedinLink;
    }

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

    public CategoryType getType() {
        return type;
    }

    public void setType(CategoryType type) {
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
}
