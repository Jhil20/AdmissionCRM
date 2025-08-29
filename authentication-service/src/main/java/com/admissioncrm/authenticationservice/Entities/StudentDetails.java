package com.admissioncrm.authenticationservice.Entities;

import com.admissioncrm.authenticationservice.Entities.CoreEntities.User;
import jakarta.persistence.*;

@Entity
public class StudentDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id") // same ID as Users table
    private User user;

    private String preferredCourse;
    private String educationLevel;
    private String gender;
    // more student-specific fields


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

    public String getPreferredCourse() {
        return preferredCourse;
    }

    public void setPreferredCourse(String preferredCourse) {
        this.preferredCourse = preferredCourse;
    }

    public String getEducationLevel() {
        return educationLevel;
    }

    public void setEducationLevel(String educationLevel) {
        this.educationLevel = educationLevel;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
