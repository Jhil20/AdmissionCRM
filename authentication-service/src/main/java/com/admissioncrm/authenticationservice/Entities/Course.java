package com.admissioncrm.authenticationservice.Entities;

import com.admissioncrm.authenticationservice.Enums.DegreeType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "courses")
public class Course {

    public Course() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "course_code", unique = true, nullable = false, length = 50)
    private String courseCode;

    @Column(name = "description", length = 500)
    private String description;

    @Column(name = "years")
    private Integer years;

    @Column(name = "semester")
    private Integer semester;

    @Enumerated(EnumType.STRING)
    @Column(name = "degree_type", length = 20)
    private DegreeType degreeType;

    @Column(name = "eligibility_criteria", columnDefinition = "TEXT")
    private String eligibilityCriteria;

    @Column(name = "total_seats_acpc")
    private Integer totalSeatsAcpc;

    @Column(name = "total_seats_management")
    private Integer totalSeatsManagement;

    @Column(name = "total_seats_nri")
    private Integer totalSeatsNri;

    @Column(name = "available_seats_acpc")
    private Integer availableSeatsAcpc;

    @Column(name = "available_seats_management")
    private Integer availableSeatsManagement;

    @Column(name = "available_seats_nri")
    private Integer availableSeatsNri;

    @Column(name = "fee", precision = 10, scale = 2)
    private BigDecimal fee;

    @Column(name = "admission_status", nullable = false)
    private Boolean admissionStatus; // defa


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "institutionId", nullable = false,referencedColumnName = "id")
    private Institution institutionId;

    @Column(name = "admission_start_date")
    private LocalDate admissionStartDate;

    @Column(name = "admission_end_date")
    private LocalDate admissionEndDate;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public Course(String id, String name, String courseCode, String description, Integer years, Integer semester, DegreeType degreeType, String eligibilityCriteria, Integer totalSeatsAcpc, Integer totalSeatsManagement, Integer totalSeatsNri, Integer availableSeatsAcpc, Integer availableSeatsManagement, Integer availableSeatsNri, BigDecimal fee, Boolean admissionStatus,Institution institutionId, LocalDate admissionStartDate, LocalDate admissionEndDate, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.courseCode = courseCode;
        this.description = description;
        this.years = years;
        this.semester = semester;
        this.degreeType = degreeType;
        this.eligibilityCriteria = eligibilityCriteria;
        this.totalSeatsAcpc = totalSeatsAcpc;
        this.totalSeatsManagement = totalSeatsManagement;
        this.totalSeatsNri = totalSeatsNri;
        this.availableSeatsAcpc = availableSeatsAcpc;
        this.availableSeatsManagement = availableSeatsManagement;
        this.availableSeatsNri = availableSeatsNri;
        this.fee = fee;
        this.admissionStatus=admissionStatus;
        this.institutionId = institutionId;
        this.admissionStartDate = admissionStartDate;
        this.admissionEndDate = admissionEndDate;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getYears() {
        return years;
    }

    public void setYears(Integer years) {
        this.years = years;
    }

    public Integer getSemester() {
        return semester;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
    }

    public DegreeType getDegreeType() {
        return degreeType;
    }

    public void setDegreeType(DegreeType degreeType) {
        this.degreeType = degreeType;
    }

    public String getEligibilityCriteria() {
        return eligibilityCriteria;
    }

    public void setEligibilityCriteria(String eligibilityCriteria) {
        this.eligibilityCriteria = eligibilityCriteria;
    }

    public Integer getTotalSeatsAcpc() {
        return totalSeatsAcpc;
    }

    public void setTotalSeatsAcpc(Integer totalSeatsAcpc) {
        this.totalSeatsAcpc = totalSeatsAcpc;
    }

    public Integer getTotalSeatsManagement() {
        return totalSeatsManagement;
    }

    public void setTotalSeatsManagement(Integer totalSeatsManagement) {
        this.totalSeatsManagement = totalSeatsManagement;
    }

    public Integer getTotalSeatsNri() {
        return totalSeatsNri;
    }

    public void setTotalSeatsNri(Integer totalSeatsNri) {
        this.totalSeatsNri = totalSeatsNri;
    }

    public Integer getAvailableSeatsAcpc() {
        return availableSeatsAcpc;
    }

    public void setAvailableSeatsAcpc(Integer availableSeatsAcpc) {
        this.availableSeatsAcpc = availableSeatsAcpc;
    }

    public Integer getAvailableSeatsManagement() {
        return availableSeatsManagement;
    }

    public void setAvailableSeatsManagement(Integer availableSeatsManagement) {
        this.availableSeatsManagement = availableSeatsManagement;
    }

    public Integer getAvailableSeatsNri() {
        return availableSeatsNri;
    }

    public void setAvailableSeatsNri(Integer availableSeatsNri) {
        this.availableSeatsNri = availableSeatsNri;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    public Boolean getAdmissionStatus() {
        return admissionStatus;
    }

    public void setAdmissionStatus(Boolean admissionStatus) {
        this.admissionStatus = admissionStatus;
    }

    public Institution getInstitutionId() {
        return institutionId;
    }

    public void setInstitutionId(Institution institutionId) {
        this.institutionId = institutionId;
    }

    public LocalDate getAdmissionStartDate() {
        return admissionStartDate;
    }

    public void setAdmissionStartDate(LocalDate admissionStartDate) {
        this.admissionStartDate = admissionStartDate;
    }

    public LocalDate getAdmissionEndDate() {
        return admissionEndDate;
    }

    public void setAdmissionEndDate(LocalDate admissionEndDate) {
        this.admissionEndDate = admissionEndDate;
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
