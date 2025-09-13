package com.admissioncrm.authenticationservice.DTO.iAdminCounsellorResponse;

import com.admissioncrm.authenticationservice.Enums.Institute;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class InstituteAdminResponseDTO {
    private String firstname;
    private String lastname;
    private String mobileNumber;
    private String username;
    private String email;
    private boolean isActive;
    private String role;
    private String instituteName;
    private String alternateEmail;
    private String designation;

    public InstituteAdminResponseDTO(boolean isActive,String firstname, String lastname, String username, String email, String mobileNumber, String alternateEmail, String designation, String instituteName) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.mobileNumber = mobileNumber;
        this.username = username;
        this.email = email;
        this.instituteName = instituteName;
        this.alternateEmail = alternateEmail;
        this.designation = designation;
        this.isActive = isActive;
    }

    public InstituteAdminResponseDTO(String firstname, String lastname, String mobileNumber, String username, String email, boolean isActive, String role, String instituteName, String alternateEmail, String designation) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.mobileNumber = mobileNumber;
        this.username = username;
        this.email = email;
        this.isActive = isActive;
        this.role = role;
        this.instituteName = instituteName;
        this.alternateEmail = alternateEmail;
        this.designation = designation;
    }

    public InstituteAdminResponseDTO() {
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

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getInstituteName() {
        return instituteName;
    }

    public void setInstituteName(String instituteName) {
        this.instituteName = instituteName;
    }

    public String getAlternateEmail() {
        return alternateEmail;
    }

    public void setAlternateEmail(String alternateEmail) {
        this.alternateEmail = alternateEmail;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }
}
