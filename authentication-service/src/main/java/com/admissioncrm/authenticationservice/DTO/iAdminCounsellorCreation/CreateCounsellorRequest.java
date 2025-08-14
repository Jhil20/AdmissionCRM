package com.admissioncrm.authenticationservice.DTO.iAdminCounsellorCreation;

import lombok.Data;
import lombok.EqualsAndHashCode;

// Counsellor creation DTO
@Data
@EqualsAndHashCode(callSuper = true)
public class CreateCounsellorRequest extends UserCreateRequest {

    private String specialization;
    private String bio;
    private String instituteName;

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

    public String getInstituteName() {
        return instituteName;
    }

    public void setInstituteName(String instituteName) {
        this.instituteName = instituteName;
    }
}
