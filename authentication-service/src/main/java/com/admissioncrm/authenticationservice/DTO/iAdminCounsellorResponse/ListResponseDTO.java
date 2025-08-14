package com.admissioncrm.authenticationservice.DTO.iAdminCounsellorResponse;

import com.admissioncrm.authenticationservice.Entities.CoreEntities.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor  // generates the constructor matching JPQL
@NoArgsConstructor
public class ListResponseDTO {
    private String firstName;
    private String email;
    private Role role;
    private String instituteName;
    private String mobileNumber;
    private Boolean isactive;

    public ListResponseDTO(String firstName, String email, String mobileNumber,
                           Boolean active, Role role, String instituteName) {
        this.firstName = firstName;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.isactive = active;
        this.role = role;
        this.instituteName = instituteName;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Boolean getIsactive() {
        return isactive;
    }

    public void setIsactive(Boolean isactive) {
        this.isactive = isactive;
    }

    public String getInstituteName() {
        return instituteName;
    }

    public void setInstituteName(String instituteName) {
        this.instituteName = instituteName;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }


}
