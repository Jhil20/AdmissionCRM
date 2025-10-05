package com.admissioncrm.authenticationservice.DTO.InternalDTO;

import com.admissioncrm.authenticationservice.Enums.DegreeType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseResponse {
    private String id;
    private String courseCode;
    private String name;
    private String description;
    private Integer years;
    private Integer semester;
    private DegreeType   degreeType;   // mapped from enum
    private String eligibilityCriteria;
    private Integer totalSeatsAcpc;
    private Integer totalSeatsManagement;
    private Integer totalSeatsNri;
    private Integer availableSeatsAcpc;
    private Integer availableSeatsManagement;
    private Integer availableSeatsNri;
    private BigDecimal fee;
    private Boolean admissionStatus;

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getCourseCode() { return courseCode; }
    public void setCourseCode(String courseCode) { this.courseCode = courseCode; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Integer getYears() { return years; }
    public void setYears(Integer years) { this.years = years; }

    public Integer getSemester() { return semester; }
    public void setSemester(Integer semester) { this.semester = semester; }

    public DegreeType getDegreeType() { return degreeType; }
    public void setDegreeType(DegreeType degreeType) { this.degreeType = degreeType; }

    public String getEligibilityCriteria() { return eligibilityCriteria; }
    public void setEligibilityCriteria(String eligibilityCriteria) { this.eligibilityCriteria = eligibilityCriteria; }

    public Integer getTotalSeatsAcpc() { return totalSeatsAcpc; }
    public void setTotalSeatsAcpc(Integer totalSeatsAcpc) { this.totalSeatsAcpc = totalSeatsAcpc; }

    public Integer getTotalSeatsManagement() { return totalSeatsManagement; }
    public void setTotalSeatsManagement(Integer totalSeatsManagement) { this.totalSeatsManagement = totalSeatsManagement; }

    public Integer getTotalSeatsNri() { return totalSeatsNri; }
    public void setTotalSeatsNri(Integer totalSeatsNri) { this.totalSeatsNri = totalSeatsNri; }

    public Integer getAvailableSeatsAcpc() { return availableSeatsAcpc; }
    public void setAvailableSeatsAcpc(Integer availableSeatsAcpc) { this.availableSeatsAcpc = availableSeatsAcpc; }

    public Integer getAvailableSeatsManagement() { return availableSeatsManagement; }
    public void setAvailableSeatsManagement(Integer availableSeatsManagement) { this.availableSeatsManagement = availableSeatsManagement; }

    public Integer getAvailableSeatsNri() { return availableSeatsNri; }
    public void setAvailableSeatsNri(Integer availableSeatsNri) { this.availableSeatsNri = availableSeatsNri; }

    public BigDecimal getFee() { return fee; }
    public void setFee(BigDecimal fee) { this.fee = fee; }

    public Boolean getAdmissionStatus() { return admissionStatus; }
    public void setAdmissionStatus(Boolean admissionStatus) { this.admissionStatus = admissionStatus; }

    // Builder
    public static class Builder {
        private final CourseResponse courseResponse = new CourseResponse();

        public Builder id(String id) { courseResponse.setId(id); return this; }
        public Builder courseCode(String courseCode) { courseResponse.setCourseCode(courseCode); return this; }
        public Builder name(String name) { courseResponse.setName(name); return this; }
        public Builder description(String description) { courseResponse.setDescription(description); return this; }
        public Builder years(Integer years) { courseResponse.setYears(years); return this; }
        public Builder semester(Integer semester) { courseResponse.setSemester(semester); return this; }
        public Builder degreeType(DegreeType degreeType) { courseResponse.setDegreeType(degreeType); return this; }
        public Builder eligibilityCriteria(String eligibilityCriteria) { courseResponse.setEligibilityCriteria(eligibilityCriteria); return this; }
        public Builder totalSeatsAcpc(Integer totalSeatsAcpc) { courseResponse.setTotalSeatsAcpc(totalSeatsAcpc); return this; }
        public Builder totalSeatsManagement(Integer totalSeatsManagement) { courseResponse.setTotalSeatsManagement(totalSeatsManagement); return this; }
        public Builder totalSeatsNri(Integer totalSeatsNri) { courseResponse.setTotalSeatsNri(totalSeatsNri); return this; }
        public Builder availableSeatsAcpc(Integer availableSeatsAcpc) { courseResponse.setAvailableSeatsAcpc(availableSeatsAcpc); return this; }
        public Builder availableSeatsManagement(Integer availableSeatsManagement) { courseResponse.setAvailableSeatsManagement(availableSeatsManagement); return this; }
        public Builder availableSeatsNri(Integer availableSeatsNri) { courseResponse.setAvailableSeatsNri(availableSeatsNri); return this; }
        public Builder fee(BigDecimal fee) { courseResponse.setFee(fee); return this; }
        public Builder admissionStatus(Boolean admissionStatus) { courseResponse.setAdmissionStatus(admissionStatus); return this; }

        public CourseResponse build() {
            return courseResponse;
        }
    }

    public static Builder builder() {
        return new Builder();
    }
}
