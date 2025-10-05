package org.example.leadmanagement.DTO.AdmissionInquiry.Request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.leadmanagement.Enum.Category;
import org.example.leadmanagement.Enum.Gender;
import org.example.leadmanagement.Enum.Program;

import java.time.LocalDate;

@Data
public class AdmissionInquiryCreateReq {
    // ---------- Personal Information ----------
    @NotBlank(message = "First name is required")
    @Size(max = 50, message = "First name must not exceed 50 characters")
    private String firstName;

    @Size(max = 50, message = "Middle name must not exceed 50 characters")
    private String middleName;  // Optional

    @NotBlank(message = "Last name is required")
    @Size(max = 50, message = "Last name must not exceed 50 characters")
    private String lastName;

    @NotBlank(message = "City is required")
    @Size(max = 100, message = "City name must not exceed 100 characters")
    private String city;

    @NotBlank(message = "State is required")
    @Size(max = 100, message = "State name must not exceed 100 characters")
    private String state;

    @NotBlank(message = "Country is required")
    @Size(max = 100, message = "Country name must not exceed 100 characters")
    private String country;

    @NotNull(message = "Gender is required")
    private Gender gender; // Enum {MALE, FEMALE, OTHER}

    @NotNull(message = "Date of birth is required")
    @Past(message = "Date of birth must be in the past")
    private LocalDate dob;

    // ---------- Academic Information ----------
    @NotNull(message = "GUJCET marks are required")
    @DecimalMin(value = "0.0", inclusive = true, message = "GUJCET marks cannot be negative")
    private Double gujcetMarks;

    @NotNull(message = "GUJCET percentile rank is required")
    @DecimalMin(value = "0.0", inclusive = true, message = "GUJCET percentile cannot be negative")
    @DecimalMax(value = "100.0", inclusive = true, message = "GUJCET percentile cannot exceed 100")
    private Double gujcetPercentileRank;

    @DecimalMin(value = "0.0", inclusive = true, message = "ACPC rank cannot be negative")
    private Integer acpcRank;  // Optional → can be null

    @NotNull(message = "12th standard percentile rank is required")
    @DecimalMin(value = "0.0", inclusive = true, message = "12th percentile cannot be negative")
    @DecimalMax(value = "100.0", inclusive = true, message = "12th percentile cannot exceed 100")
    private Double twelfthPercentileRank;

    @NotNull(message = "Program is required")
    private Program program; // Enum {UNDERGRADUATE, POSTGRADUATE, PHD}

    // ---------- Institute Information ----------
    @NotBlank(message = "Institute name is required")
    @Size(max = 200, message = "Institute name must not exceed 200 characters")
    private String instituteName;

//    @NotNull
    private String instituteId;

    @NotBlank(message = "Course name is required")
    @Size(max = 200, message = "Course name must not exceed 200 characters")
    private String courseName;

//    @NotNull
    private String courseId;

    @NotNull(message = "Category is required")
    private Category category;

    public @NotBlank(message = "First name is required") @Size(max = 50, message = "First name must not exceed 50 characters") String getFirstName() {
        return firstName;
    }

    public void setFirstName(@NotBlank(message = "First name is required") @Size(max = 50, message = "First name must not exceed 50 characters") String firstName) {
        this.firstName = firstName;
    }

    public @Size(max = 50, message = "Middle name must not exceed 50 characters") String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(@Size(max = 50, message = "Middle name must not exceed 50 characters") String middleName) {
        this.middleName = middleName;
    }

    public @NotBlank(message = "Last name is required") @Size(max = 50, message = "Last name must not exceed 50 characters") String getLastName() {
        return lastName;
    }

    public void setLastName(@NotBlank(message = "Last name is required") @Size(max = 50, message = "Last name must not exceed 50 characters") String lastName) {
        this.lastName = lastName;
    }

    public @NotBlank(message = "City is required") @Size(max = 100, message = "City name must not exceed 100 characters") String getCity() {
        return city;
    }

    public void setCity(@NotBlank(message = "City is required") @Size(max = 100, message = "City name must not exceed 100 characters") String city) {
        this.city = city;
    }

    public @NotBlank(message = "State is required") @Size(max = 100, message = "State name must not exceed 100 characters") String getState() {
        return state;
    }

    public void setState(@NotBlank(message = "State is required") @Size(max = 100, message = "State name must not exceed 100 characters") String state) {
        this.state = state;
    }

    public @NotBlank(message = "Country is required") @Size(max = 100, message = "Country name must not exceed 100 characters") String getCountry() {
        return country;
    }

    public void setCountry(@NotBlank(message = "Country is required") @Size(max = 100, message = "Country name must not exceed 100 characters") String country) {
        this.country = country;
    }

    public @NotNull(message = "Gender is required") Gender getGender() {
        return gender;
    }

    public void setGender(@NotNull(message = "Gender is required") Gender gender) {
        this.gender = gender;
    }

    public @NotNull(message = "Date of birth is required") @Past(message = "Date of birth must be in the past") LocalDate getDob() {
        return dob;
    }

    public void setDob(@NotNull(message = "Date of birth is required") @Past(message = "Date of birth must be in the past") LocalDate dob) {
        this.dob = dob;
    }

    public @NotNull(message = "GUJCET marks are required") @DecimalMin(value = "0.0", inclusive = true, message = "GUJCET marks cannot be negative") Double getGujcetMarks() {
        return gujcetMarks;
    }

    public void setGujcetMarks(@NotNull(message = "GUJCET marks are required") @DecimalMin(value = "0.0", inclusive = true, message = "GUJCET marks cannot be negative") Double gujcetMarks) {
        this.gujcetMarks = gujcetMarks;
    }

    public @NotNull(message = "GUJCET percentile rank is required") @DecimalMin(value = "0.0", inclusive = true, message = "GUJCET percentile cannot be negative") @DecimalMax(value = "100.0", inclusive = true, message = "GUJCET percentile cannot exceed 100") Double getGujcetPercentileRank() {
        return gujcetPercentileRank;
    }

    public void setGujcetPercentileRank(@NotNull(message = "GUJCET percentile rank is required") @DecimalMin(value = "0.0", inclusive = true, message = "GUJCET percentile cannot be negative") @DecimalMax(value = "100.0", inclusive = true, message = "GUJCET percentile cannot exceed 100") Double gujcetPercentileRank) {
        this.gujcetPercentileRank = gujcetPercentileRank;
    }

    public @DecimalMin(value = "0.0", inclusive = true, message = "ACPC rank cannot be negative") Integer getAcpcRank() {
        return acpcRank;
    }

    public void setAcpcRank(@DecimalMin(value = "0.0", inclusive = true, message = "ACPC rank cannot be negative") Integer acpcRank) {
        this.acpcRank = acpcRank;
    }

    public @NotNull(message = "12th standard percentile rank is required") @DecimalMin(value = "0.0", inclusive = true, message = "12th percentile cannot be negative") @DecimalMax(value = "100.0", inclusive = true, message = "12th percentile cannot exceed 100") Double getTwelfthPercentileRank() {
        return twelfthPercentileRank;
    }

    public void setTwelfthPercentileRank(@NotNull(message = "12th standard percentile rank is required") @DecimalMin(value = "0.0", inclusive = true, message = "12th percentile cannot be negative") @DecimalMax(value = "100.0", inclusive = true, message = "12th percentile cannot exceed 100") Double twelfthPercentileRank) {
        this.twelfthPercentileRank = twelfthPercentileRank;
    }

    public @NotNull(message = "Program is required") Program getProgram() {
        return program;
    }

    public void setProgram(@NotNull(message = "Program is required") Program program) {
        this.program = program;
    }

    public @NotBlank(message = "Institute name is required") @Size(max = 200, message = "Institute name must not exceed 200 characters") String getInstituteName() {
        return instituteName;
    }

    public void setInstituteName(@NotBlank(message = "Institute name is required") @Size(max = 200, message = "Institute name must not exceed 200 characters") String instituteName) {
        this.instituteName = instituteName;
    }


    public @NotBlank(message = "Course name is required") @Size(max = 200, message = "Course name must not exceed 200 characters") String getCourseName() {
        return courseName;
    }

    public void setCourseName(@NotBlank(message = "Course name is required") @Size(max = 200, message = "Course name must not exceed 200 characters") String courseName) {
        this.courseName = courseName;
    }


    public @NotNull(message = "Category is required") Category getCategory() {
        return category;
    }

    public void setCategory(@NotNull(message = "Category is required") Category category) {
        this.category = category;
    }

    public String getInstituteId() {
        return instituteId;
    }

    public void setInstituteId(String instituteId) {
        this.instituteId = instituteId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public AdmissionInquiryCreateReq()
    {

    }

    public AdmissionInquiryCreateReq(String firstName, String middleName, String lastName, String city, String state, String country, Gender gender, LocalDate dob, Double gujcetMarks, Double gujcetPercentileRank, Integer acpcRank, Double twelfthPercentileRank, Program program, String instituteName, String courseName, Category category) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.city = city;
        this.state = state;
        this.country = country;
        this.gender = gender;
        this.dob = dob;
        this.gujcetMarks = gujcetMarks;
        this.gujcetPercentileRank = gujcetPercentileRank;
        this.acpcRank = acpcRank;
        this.twelfthPercentileRank = twelfthPercentileRank;
        this.program = program;
        this.instituteName = instituteName;
        this.courseName = courseName;
        this.category = category;
    }

    public AdmissionInquiryCreateReq(String firstName, String middleName, String lastName, String city, String state, String country, Gender gender, LocalDate dob, Double gujcetMarks, Double gujcetPercentileRank, Integer acpcRank, Double twelfthPercentileRank, Program program, String instituteName, String instituteId, String courseName, String courseId, Category category) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.city = city;
        this.state = state;
        this.country = country;
        this.gender = gender;
        this.dob = dob;
        this.gujcetMarks = gujcetMarks;
        this.gujcetPercentileRank = gujcetPercentileRank;
        this.acpcRank = acpcRank;
        this.twelfthPercentileRank = twelfthPercentileRank;
        this.program = program;
        this.instituteName = instituteName;
        this.instituteId = instituteId;
        this.courseName = courseName;
        this.courseId = courseId;
        this.category = category;
    }
}
