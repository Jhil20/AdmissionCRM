package org.example.leadmanagement.DTO.Lead.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class LeadCreationReq {

    private String applicationInquiryId;

    private String queryId;

    public String getApplicationInquiryId() {
        return applicationInquiryId;
    }

    public void setApplicationInquiryId(String applicationInquiryId) {
        this.applicationInquiryId = applicationInquiryId;
    }

    public String getQueryId() {
        return queryId;
    }

    public void setQueryId(String queryId) {
        this.queryId = queryId;
    }

    public LeadCreationReq(String applicationInquiryId, String queryId) {
        this.applicationInquiryId = applicationInquiryId;
        this.queryId = queryId;
    }

    public LeadCreationReq() {

    }
}
