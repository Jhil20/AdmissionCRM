package com.admissioncrm.authenticationservice.DTO.iAdminCounsellorResponse;

import com.admissioncrm.authenticationservice.Entities.CoreEntities.Role;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CounsellorResponseDTO {
    private String id;
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private String mobileNumber;
    private Boolean isActive;
    private Role role;
    private String specialization;
    private String bio;
    private String instituteName;

    public CounsellorResponseDTO(String id,String userName, String firstName, String lastName, String email, String mobileNumber, Boolean isActive, Role role, String specialization, String bio, String instituteName) {
        this.id = id;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.isActive = isActive;
        this.role = role;
        this.specialization = specialization;
        this.bio = bio;
        this.instituteName = instituteName;
    }

    public CounsellorResponseDTO(boolean isActive,String firstName, String lastName, String userName, String email, String mobileNumber, String specialization, String bio, String instituteName) {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.specialization = specialization;
        this.bio = bio;
        this.instituteName = instituteName;
        this.isActive = isActive;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

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

