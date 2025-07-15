package com.admissioncrm.authenticationservice.Entities.CoreEntities;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class User  {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(unique = true, nullable = false, length = 50)
    private String username;

    @Column(unique = true, nullable = false, length = 100)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "first_name", length = 50)
    private String firstName;

    @Column(name = "last_name", length = 50)
    private String lastName;

    @Column(length = 15)
    private String mobileNumber;

    @Enumerated(EnumType.STRING)
    private Role role = Role.STUDENT;

//    @JoinColumn(name = "university_id")
//    private String universityId;
//
//    @JoinColumn(name = "institution_id")
//    private String institutionId;

//    @OneToMany(mappedBy = "assignedCounselor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ElementCollection
    @CollectionTable(name = "assigned_leads", joinColumns = @JoinColumn(name = "user_id"))
    private List<String> assignedLeads = new ArrayList<>();

    @Column(name = "max_leads_assignment")
    private Integer maxLeadsAssignment = 50;

    @Column(name = "current_leads_count")
    private Integer currentLeadsCount = 0;

    @Column(name = "is_active")
    private Boolean isActive = true;

    @Column(name = "last_login")
    private LocalDateTime lastLogin;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // Helper methods
    public boolean isMainAdmin() {
        return role != null && "UNIVERSITY_ADMIN".equals(role.name());
    }

    public boolean isInstituteAdmin() {
        return role != null && "INSTITUTE_ADMIN".equals(role.name());
    }

    public boolean isCounselor() {
        return role != null && "COUNSELOR".equals(role.name());
    }

    public boolean isStudent() {
        return role != null && "STUDENT".equals(role.name());
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<String> getAssignedLeads() {
        return assignedLeads;
    }

    public void setAssignedLeads(List<String> assignedLeads) {
        this.assignedLeads = assignedLeads;
    }

    public Integer getMaxLeadsAssignment() {
        return maxLeadsAssignment;
    }

    public void setMaxLeadsAssignment(Integer maxLeadsAssignment) {
        this.maxLeadsAssignment = maxLeadsAssignment;
    }

    public Integer getCurrentLeadsCount() {
        return currentLeadsCount;
    }

    public void setCurrentLeadsCount(Integer currentLeadsCount) {
        this.currentLeadsCount = currentLeadsCount;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public LocalDateTime getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(LocalDateTime lastLogin) {
        this.lastLogin = lastLogin;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

}
