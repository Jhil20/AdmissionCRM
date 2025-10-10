package com.admissioncrm.authenticationservice.DTO.iAdminCounsellorResponse;

import com.admissioncrm.authenticationservice.Enums.Institute;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class InstituteAdminResponseDTO {
    private String firstName;
    private String lastName;
    private String mobileNumber;
    private String username;
    private String email;
    private boolean isActive;
    private String role;
    private String instituteName;
    private String alternateEmail;
    private String designation;

    public InstituteAdminResponseDTO(boolean isActive,String firstName, String lastName, String username, String email, String mobileNumber, String alternateEmail, String designation, String instituteName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobileNumber = mobileNumber;
        this.username = username;
        this.email = email;
        this.instituteName = instituteName;
        this.alternateEmail = alternateEmail;
        this.designation = designation;
        this.isActive = isActive;
    }

    public InstituteAdminResponseDTO(String firstName, String lastName, String mobileNumber, String username, String email, boolean isActive, String role, String instituteName, String alternateEmail, String designation) {
        this.firstName = firstName;
        this.lastName = lastName;
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


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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
