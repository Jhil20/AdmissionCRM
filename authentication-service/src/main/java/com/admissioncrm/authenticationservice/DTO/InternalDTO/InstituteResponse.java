package com.admissioncrm.authenticationservice.DTO.InternalDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InstituteResponse {

    private String id;

    // Basic Identity
    private String institutionCode;
    private String instituteName;
    private String type;          // e.g., "Engineering College", "Medical Institute", etc.
    private String description;

    // Location Details
    private String address;
    private String city;
    private String state;
    private String pincode;

    // Contact Info
    private String contactEmail;
    private String contactNumber;
    private Integer establishedYear;
    private String principalName;

    // Operational Configuration
    private Integer maxLeadsAllowed;
    private Boolean isActive;

    // Branding
    private String logoUrl;
    private String websiteUrl;
    private String tagline;
    private String facebookLink;
    private String instagramLink;
    private String linkedinLink;

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

    public static class Builder {
        private String id;
        private String institutionCode;
        private String instituteName;
        private String type;
        private String description;
        private String address;
        private String city;
        private String state;
        private String pincode;
        private String contactEmail;
        private String contactNumber;
        private Integer establishedYear;
        private String principalName;
        private Integer maxLeadsAllowed;
        private Boolean isActive;
        private String logoUrl;
        private String websiteUrl;
        private String tagline;
        private String facebookLink;
        private String instagramLink;
        private String linkedinLink;

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder institutionCode(String institutionCode) {
            this.institutionCode = institutionCode;
            return this;
        }

        public Builder instituteName(String instituteName) {
            this.instituteName = instituteName;
            return this;
        }

        public Builder type(String type) {
            this.type = type;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder address(String address) {
            this.address = address;
            return this;
        }

        public Builder city(String city) {
            this.city = city;
            return this;
        }

        public Builder state(String state) {
            this.state = state;
            return this;
        }

        public Builder pincode(String pincode) {
            this.pincode = pincode;
            return this;
        }

        public Builder contactEmail(String contactEmail) {
            this.contactEmail = contactEmail;
            return this;
        }

        public Builder contactNumber(String contactNumber) {
            this.contactNumber = contactNumber;
            return this;
        }

        public Builder establishedYear(Integer establishedYear) {
            this.establishedYear = establishedYear;
            return this;
        }

        public Builder principalName(String principalName) {
            this.principalName = principalName;
            return this;
        }

        public Builder maxLeadsAllowed(Integer maxLeadsAllowed) {
            this.maxLeadsAllowed = maxLeadsAllowed;
            return this;
        }

        public Builder isActive(Boolean isActive) {
            this.isActive = isActive;
            return this;
        }

        public Builder logoUrl(String logoUrl) {
            this.logoUrl = logoUrl;
            return this;
        }

        public Builder websiteUrl(String websiteUrl) {
            this.websiteUrl = websiteUrl;
            return this;
        }

        public Builder tagline(String tagline) {
            this.tagline = tagline;
            return this;
        }

        public Builder facebookLink(String facebookLink) {
            this.facebookLink = facebookLink;
            return this;
        }

        public Builder instagramLink(String instagramLink) {
            this.instagramLink = instagramLink;
            return this;
        }

        public Builder linkedinLink(String linkedinLink) {
            this.linkedinLink = linkedinLink;
            return this;
        }

        public InstituteResponse build() {
            InstituteResponse response = new InstituteResponse();
            response.setId(this.id);
            response.setInstitutionCode(this.institutionCode);
            response.setInstituteName(this.instituteName);
            response.setType(this.type);
            response.setDescription(this.description);
            response.setAddress(this.address);
            response.setCity(this.city);
            response.setState(this.state);
            response.setPincode(this.pincode);
            response.setContactEmail(this.contactEmail);
            response.setContactNumber(this.contactNumber);
            response.setEstablishedYear(this.establishedYear);
            response.setPrincipalName(this.principalName);
            response.setMaxLeadsAllowed(this.maxLeadsAllowed);
            response.setActive(this.isActive);
            response.setLogoUrl(this.logoUrl);
            response.setWebsiteUrl(this.websiteUrl);
            response.setTagline(this.tagline);
            response.setFacebookLink(this.facebookLink);
            response.setInstagramLink(this.instagramLink);
            response.setLinkedinLink(this.linkedinLink);
            return response;
        }
    }

    // Static method to get builder
    public static Builder builder() {
        return new Builder();
    }
}
