package com.admissioncrm.authenticationservice.Entities;

import com.admissioncrm.authenticationservice.Entities.CoreEntities.User;
import jakarta.persistence.*;

@Entity
public class CounsellorDetails {
    @Id
    private String id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private User user;

    private String assignedInstitute;
    private String expertiseArea;
    // more counsellor-specific fields


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getAssignedInstitute() {
        return assignedInstitute;
    }

    public void setAssignedInstitute(String assignedInstitute) {
        this.assignedInstitute = assignedInstitute;
    }

    public String getExpertiseArea() {
        return expertiseArea;
    }

    public void setExpertiseArea(String expertiseArea) {
        this.expertiseArea = expertiseArea;
    }
}
