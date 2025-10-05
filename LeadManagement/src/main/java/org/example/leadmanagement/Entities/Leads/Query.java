package org.example.leadmanagement.Entities.Leads;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.example.leadmanagement.Enum.QuerySubject;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "queries")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Query {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private String id;

    // Enum for query subject
    @Enumerated(EnumType.STRING)
    @Column(name = "query_subject", nullable = false, length = 50)
    private QuerySubject querySubject;

    @Column(name = "query_description",nullable = false,columnDefinition = "TEXT")
    @Lob
    @NotNull(message = "Query cannot be null")
    private String query;

    @Column(name = "institute_id",nullable = false, length = 150)
    private String instituteId;

    @Column(name = "institute_name", nullable = false, length = 150)
    private String instituteName;

    @Column(name = "query_remark", length = 500)
    private String queryRemark;

    // timestamps
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // Many queries can belong to one admission inquiry
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inquiry_id", nullable = true, foreignKey = @ForeignKey(name = "fk_query_inquiry"))
    private AdmissionInquiry admissionInquiry;

    @OneToMany(mappedBy = "query",cascade = CascadeType.ALL,orphanRemoval = false)
    private List<Lead> lead;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public QuerySubject getQuerySubject() {
        return querySubject;
    }

    public void setQuerySubject(QuerySubject querySubject) {
        this.querySubject = querySubject;
    }

    public @NotNull(message = "Query cannot be null") String getQuery() {
        return query;
    }

    public void setQuery(@NotNull(message = "Query cannot be null") String query) {
        this.query = query;
    }

    public String getInstituteId() {
        return instituteId;
    }

    public void setInstituteId(String instituteId) {
        this.instituteId = instituteId;
    }

    public String getInstituteName() {
        return instituteName;
    }

    public void setInstituteName(String instituteName) {
        this.instituteName = instituteName;
    }

    public String getQueryRemark() {
        return queryRemark;
    }

    public void setQueryRemark(String queryRemark) {
        this.queryRemark = queryRemark;
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

    public AdmissionInquiry getAdmissionInquiry() {
        return admissionInquiry;
    }

    public void setAdmissionInquiry(AdmissionInquiry admissionInquiry) {
        this.admissionInquiry = admissionInquiry;
    }

    public List<Lead> getLead() {
        return lead;
    }

    public void setLead(List<Lead> lead) {
        this.lead = lead;
    }

    public Query ()
    {

    }
}
