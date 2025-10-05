package org.example.leadmanagement.Entities.Leads;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.leadmanagement.Enum.Category;
import org.example.leadmanagement.Enum.Gender;
import org.example.leadmanagement.Enum.Program;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "AdmissionInquiry")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdmissionInquiry {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @NotBlank
    @Size(max = 50)
    @Column(name = "first_name", length = 50, nullable = false)
    private String firstName;

    @Size(max = 50)
    @Column(name = "middle_name", length = 50)
    private String middleName;

    @NotBlank
    @Size(max = 50)
    @Column(name = "last_name", length = 50, nullable = false)
    private String lastName;

    @NotBlank
    @Size(max = 100)
    @Column(length = 100, nullable = false)
    private String city;

    @NotBlank
    @Size(max = 100)
    @Column(length = 100, nullable = false)
    private String state;

    @NotBlank
    @Size(max = 100)
    @Column(length = 100, nullable = false)
    private String country;

    @Enumerated(EnumType.STRING)
    @Column(length = 10, nullable = false)
    private Gender gender;

    @Past
    @Column(name = "dob", nullable = false)
    private LocalDate dateOfBirth;

    @DecimalMin(value = "0.0", inclusive = true)
    @Column(name = "gujcet_marks")
    private Double gujcetMarks;

    @DecimalMin(value = "0.0", inclusive = true)
    @Max(100)
    @Column(name = "gujcet_percentile_rank")
    private Double gujcetPercentileRank;

    @Min(0)
    @Column(name = "acpc_rank")
    private Integer acpcRank;

    @DecimalMin(value = "0.0", inclusive = true)
    @Max(100)
    @Column(name = "twelfth_percentile_rank")
    private Double twelfthPercentileRank;

    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    private Program program;

    @NotNull
    @Column(name = "institute_id", nullable = false)
    private String instituteId;   // FK (different service)

    @NotBlank
    @Size(max = 150)
    @Column(name = "institute_name", length = 150, nullable = false)
    private String instituteName;

    @NotNull
    @Column(name = "course_id", nullable = false)
    private String courseId;      // FK (different service)

    @NotBlank
    @Size(max = 150)
    @Column(name = "course_name", length = 150, nullable = false)
    private String courseName;

    @Enumerated(EnumType.STRING)
    @Column(length = 30, nullable = false)
    private Category category;

    // Always fixed value
    @Column(nullable = false, updatable = false, length = 30)
    private final String subject = "Admission Inquiry";

    @NotNull
    @Column(name = "user_id", nullable = false)
    private String userId;  // FK (different service)

    @OneToMany(
            mappedBy = "admissionInquiry",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private List<Query> queries;

    // One Inquiry -> Many Leads
    @OneToMany(mappedBy = "admissionInquiry", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Lead> leads;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public AdmissionInquiry(String firstName, String middleName, String lastName, String city, String state, String country, Gender gender, LocalDate dateOfBirth, Double gujcetMarks, Double gujcetPercentileRank, Integer acpcRank, Double twelfthPercentileRank, Program program, String instituteId, String instituteName, String courseId, String courseName, Category category, String userId) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.city = city;
        this.state = state;
        this.country = country;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.gujcetMarks = gujcetMarks;
        this.gujcetPercentileRank = gujcetPercentileRank;
        this.acpcRank = acpcRank;
        this.twelfthPercentileRank = twelfthPercentileRank;
        this.program = program;
        this.instituteId = instituteId;
        this.instituteName = instituteName;
        this.courseId = courseId;
        this.courseName = courseName;
        this.category = category;
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public @NotBlank @Size(max = 50) String getFirstName() {
        return firstName;
    }

    public void setFirstName(@NotBlank @Size(max = 50) String firstName) {
        this.firstName = firstName;
    }

    public @Size(max = 50) String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(@Size(max = 50) String middleName) {
        this.middleName = middleName;
    }

    public @NotBlank @Size(max = 50) String getLastName() {
        return lastName;
    }

    public void setLastName(@NotBlank @Size(max = 50) String lastName) {
        this.lastName = lastName;
    }

    public @NotBlank @Size(max = 100) String getCity() {
        return city;
    }

    public void setCity(@NotBlank @Size(max = 100) String city) {
        this.city = city;
    }

    public @NotBlank @Size(max = 100) String getState() {
        return state;
    }

    public void setState(@NotBlank @Size(max = 100) String state) {
        this.state = state;
    }

    public @NotBlank @Size(max = 100) String getCountry() {
        return country;
    }

    public void setCountry(@NotBlank @Size(max = 100) String country) {
        this.country = country;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public @Past LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(@Past LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public @DecimalMin(value = "0.0", inclusive = true) Double getGujcetMarks() {
        return gujcetMarks;
    }

    public void setGujcetMarks(@DecimalMin(value = "0.0", inclusive = true) Double gujcetMarks) {
        this.gujcetMarks = gujcetMarks;
    }

    public @DecimalMin(value = "0.0", inclusive = true) @Max(100) Double getGujcetPercentileRank() {
        return gujcetPercentileRank;
    }

    public void setGujcetPercentileRank(@DecimalMin(value = "0.0", inclusive = true) @Max(100) Double gujcetPercentileRank) {
        this.gujcetPercentileRank = gujcetPercentileRank;
    }

    public @Min(0) Integer getAcpcRank() {
        return acpcRank;
    }

    public void setAcpcRank(@Min(0) Integer acpcRank) {
        this.acpcRank = acpcRank;
    }

    public @DecimalMin(value = "0.0", inclusive = true) @Max(100) Double getTwelfthPercentileRank() {
        return twelfthPercentileRank;
    }

    public void setTwelfthPercentileRank(@DecimalMin(value = "0.0", inclusive = true) @Max(100) Double twelfthPercentileRank) {
        this.twelfthPercentileRank = twelfthPercentileRank;
    }

    public Program getProgram() {
        return program;
    }

    public void setProgram(Program program) {
        this.program = program;
    }

    public @NotNull String getInstituteId() {
        return instituteId;
    }

    public void setInstituteId(@NotNull String instituteId) {
        this.instituteId = instituteId;
    }

    public @NotBlank @Size(max = 150) String getInstituteName() {
        return instituteName;
    }

    public void setInstituteName(@NotBlank @Size(max = 150) String instituteName) {
        this.instituteName = instituteName;
    }

    public @NotNull String getCourseId() {
        return courseId;
    }

    public void setCourseId(@NotNull String courseId) {
        this.courseId = courseId;
    }

    public @NotBlank @Size(max = 150) String getCourseName() {
        return courseName;
    }

    public void setCourseName(@NotBlank @Size(max = 150) String courseName) {
        this.courseName = courseName;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getSubject() {
        return subject;
    }

    public @NotNull String getUserId() {
        return userId;
    }

    public void setUserId(@NotNull String userId) {
        this.userId = userId;
    }

    public List<Query> getQueries() {
        return queries;
    }

    public void setQueries(List<Query> queries) {
        this.queries = queries;
    }

    public List<Lead> getLeads() {
        return leads;
    }

    public void setLeads(List<Lead> leads) {
        this.leads = leads;
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

    public AdmissionInquiry()
    {

    }
}