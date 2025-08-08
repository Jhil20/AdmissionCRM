package com.admissioncrm.authenticationservice.DTO.iAdminCounsellorResponse;

import com.admissioncrm.authenticationservice.Enums.Institute;
import lombok.*;

public class CounsellorResponseDTO {
    private String email;
    private boolean isActive;
    private String mobileNumber;
    private String role;
    private String username;
    private String expertiseArea;
    private Institute institute;


    public CounsellorResponseDTO(String email, boolean isActive, String mobileNumber, String role, String username, String expertiseArea, Institute institute) {
        this.email = email;
        this.isActive = isActive;
        this.mobileNumber = mobileNumber;
        this.role = role;
        this.username = username;
        this.expertiseArea = expertiseArea;
        this.institute = institute;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getExpertiseArea() {
        return expertiseArea;
    }

    public void setExpertiseArea(String expertiseArea) {
        this.expertiseArea = expertiseArea;
    }

    public Institute getInstitute() {
        return institute;
    }

    public void setInstitute(Institute institute) {
        this.institute = institute;
    }
}
