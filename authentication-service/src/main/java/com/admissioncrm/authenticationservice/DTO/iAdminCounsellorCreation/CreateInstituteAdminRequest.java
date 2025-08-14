package com.admissioncrm.authenticationservice.DTO.iAdminCounsellorCreation;

import lombok.Data;
import lombok.EqualsAndHashCode;

// Institute Admin creation DTO
@Data
@EqualsAndHashCode(callSuper = true)
public class CreateInstituteAdminRequest extends UserCreateRequest {

    private String designation;
    private String alternateEmail;
    private String instituteName;

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getAlternateEmail() {
        return alternateEmail;
    }

    public void setAlternateEmail(String alternateEmail) {
        this.alternateEmail = alternateEmail;
    }

    public String getInstituteName() {
        return instituteName;
    }

    public void setInstituteName(String instituteName) {
        this.instituteName = instituteName;
    }
}
