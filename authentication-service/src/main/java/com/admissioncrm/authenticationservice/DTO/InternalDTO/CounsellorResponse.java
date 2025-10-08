package com.admissioncrm.authenticationservice.DTO.InternalDTO;

import com.admissioncrm.authenticationservice.Entities.CoreEntities.Role;

public class CounsellorResponse {
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
    private int maxLeadHandling;

    public CounsellorResponse() {}

    public CounsellorResponse(String id, String userName, String firstName, String lastName, String email, String mobileNumber, Boolean isActive, Role role, String specialization, String bio, String instituteName, int maxLeadHandling) {
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
        this.maxLeadHandling = maxLeadHandling;
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

    public int getMaxLeadHandling() {
        return maxLeadHandling;
    }

    public void setMaxLeadHandling(int maxLeadHandling) {
        this.maxLeadHandling = maxLeadHandling;
    }
}
