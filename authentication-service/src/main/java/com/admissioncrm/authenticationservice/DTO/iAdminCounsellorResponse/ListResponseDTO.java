package com.admissioncrm.authenticationservice.DTO.iAdminCounsellorResponse;

import com.admissioncrm.authenticationservice.Entities.CoreEntities.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data // generates the constructor matching JPQL
@NoArgsConstructor
public class ListResponseDTO {
    private String firstName;
    private String email;
    private String mobileNumber;
    private Boolean isActive;
    private Role role;
    private String specialization;
    private String bio;
    private String instituteName;
    private String designation;
    private String alternateEmail;
    private String InstituteAdminInstitute;

    public ListResponseDTO(String firstName, String email, String mobileNumber, Boolean isActive, Role role, String specialization, String bio, String instituteName, String designation, String alternateEmail, String InstituteAdminInstitute) {
        this.firstName = firstName;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.isActive = isActive;
        this.role = role;
        this.specialization = specialization;
        this.bio = bio;
        this.instituteName = instituteName;
        this.designation = designation;
        this.alternateEmail = alternateEmail;
        this.InstituteAdminInstitute = InstituteAdminInstitute;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
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

    public String getCounsellorInstitute() {
        return InstituteAdminInstitute;
    }

    public void setCounsellorInstitute(String InstituteAdminInstitute) {
        this.InstituteAdminInstitute = InstituteAdminInstitute;
    }
}
