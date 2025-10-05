package org.example.leadmanagement.DTO.Lead.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class LeadCreationReq {
    @NotBlank(message = "Application Inquiry ID is required")
    @Size(max = 100, message = "Application Inquiry ID must not exceed 100 characters")
    private String applicationInquiryId;

    @NotBlank(message = "Query ID is required")
    @Size(max = 100, message = "Query ID must not exceed 100 characters")
    private String queryId;

    public @NotBlank(message = "Application Inquiry ID is required") @Size(max = 100, message = "Application Inquiry ID must not exceed 100 characters") String getApplicationInquiryId() {
        return applicationInquiryId;
    }

    public void setApplicationInquiryId(@NotBlank(message = "Application Inquiry ID is required") @Size(max = 100, message = "Application Inquiry ID must not exceed 100 characters") String applicationInquiryId) {
        this.applicationInquiryId = applicationInquiryId;
    }

    public @NotBlank(message = "Query ID is required") @Size(max = 100, message = "Query ID must not exceed 100 characters") String getQueryId() {
        return queryId;
    }

    public void setQueryId(@NotBlank(message = "Query ID is required") @Size(max = 100, message = "Query ID must not exceed 100 characters") String queryId) {
        this.queryId = queryId;
    }

    public LeadCreationReq(String applicationInquiryId, String queryId) {
        this.applicationInquiryId = applicationInquiryId;
        this.queryId = queryId;
    }

    public LeadCreationReq() {

    }
}
