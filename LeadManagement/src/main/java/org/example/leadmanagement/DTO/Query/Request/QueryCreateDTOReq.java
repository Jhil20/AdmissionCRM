package org.example.leadmanagement.DTO.Query.Request;

import jakarta.persistence.Lob;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.leadmanagement.Enum.QuerySubject;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QueryCreateDTOReq {
    @NotNull(message = "Query subject is required")
    private QuerySubject querySubject;

    @NotBlank(message = "Query cannot be blank")
    @Lob// optional safety limit
    private String query;

    private String AdmissionInquiryId;

    @NotBlank(message = "Institute ID is required")
    @Size(max = 150, message = "Institute ID must not exceed 150 characters")
    private String instituteId;

    @NotBlank(message = "Institute name is required")
    @Size(max = 150, message = "Institute name must not exceed 150 characters")
    private String instituteName;

    public @NotNull(message = "Query subject is required") QuerySubject getQuerySubject() {
        return querySubject;
    }

    public void setQuerySubject(@NotNull(message = "Query subject is required") QuerySubject querySubject) {
        this.querySubject = querySubject;
    }

    public @NotBlank(message = "Query cannot be blank") String getQuery() {
        return query;
    }

    public void setQuery(@NotBlank(message = "Query cannot be blank") String query) {
        this.query = query;
    }

    public String getAdmissionInquiryId() {
        return AdmissionInquiryId;
    }

    public void setAdmissionInquiryId(String admissionInquiryId) {
        AdmissionInquiryId = admissionInquiryId;
    }

    public @NotBlank(message = "Institute ID is required") @Size(max = 150, message = "Institute ID must not exceed 150 characters") String getInstituteId() {
        return instituteId;
    }

    public void setInstituteId(@NotBlank(message = "Institute ID is required") @Size(max = 150, message = "Institute ID must not exceed 150 characters") String instituteId) {
        this.instituteId = instituteId;
    }

    public @NotBlank(message = "Institute name is required") @Size(max = 150, message = "Institute name must not exceed 150 characters") String getInstituteName() {
        return instituteName;
    }

    public void setInstituteName(@NotBlank(message = "Institute name is required") @Size(max = 150, message = "Institute name must not exceed 150 characters") String instituteName) {
        this.instituteName = instituteName;
    }
}
