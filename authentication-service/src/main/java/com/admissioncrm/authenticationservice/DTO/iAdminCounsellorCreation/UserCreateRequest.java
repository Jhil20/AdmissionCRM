package com.admissioncrm.authenticationservice.DTO.iAdminCounsellorCreation;

import com.admissioncrm.authenticationservice.Enums.Institute;
import jakarta.persistence.Column;
import lombok.Data;

@Data
public class UserCreateRequest {
    private String fullName;

    private String username;

    private String email;

    private String password;

    private Institute institute; //institute or DeptId to assign dept/Institute to the councellor/Admin

    private String expertiseArea;

    @Column(length = 15)
    private String mobileNumber;

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Institute getInstitute() {
        return institute;
    }

    public void setInstitute(Institute institute) {
        this.institute = institute;
    }

    public String getExpertiseArea() {
        return expertiseArea;
    }

    public void setExpertiseArea(String expertiseArea) {
        this.expertiseArea = expertiseArea;
    }
}
