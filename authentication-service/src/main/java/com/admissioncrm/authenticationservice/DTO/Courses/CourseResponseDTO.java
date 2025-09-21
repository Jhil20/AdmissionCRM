package com.admissioncrm.authenticationservice.DTO.Courses;

import com.admissioncrm.authenticationservice.Enums.DegreeType;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CourseResponseDTO {
    private String id;
    private String name;
    private String courseCode;
    private String description;
    private Integer years;
    private Integer semester;
    private DegreeType degreeType;
    private String eligibilityCriteria;
    private Integer totalSeatsAcpc;
    private Integer totalSeatsManagement;
    private Integer totalSeatsNri;
    private Integer availableSeatsAcpc;
    private Integer availableSeatsManagement;
    private Integer availableSeatsNri;
    private BigDecimal fee;
    private LocalDate admissionStartDate;
    private LocalDate admissionEndDate;

    public CourseResponseDTO(Integer availableSeatsNri, String id, String name, String courseCode, String description, Integer years, Integer semester, DegreeType degreeType, String eligibilityCriteria, Integer totalSeatsAcpc, Integer totalSeatsManagement, Integer totalSeatsNri, Integer availableSeatsAcpc, Integer availableSeatsManagement, BigDecimal fee, LocalDate admissionStartDate, LocalDate admissionEndDate) {

        this.availableSeatsNri = availableSeatsNri;
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
        this.fee = fee;
        this.admissionStartDate = admissionStartDate;
        this.admissionEndDate = admissionEndDate;
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
}
