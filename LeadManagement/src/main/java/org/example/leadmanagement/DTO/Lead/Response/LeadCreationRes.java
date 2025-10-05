package org.example.leadmanagement.DTO.Lead.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.leadmanagement.Enum.LeadStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LeadCreationRes {

    private String LeadTrackingId;
    private LeadStatus leadStatus;

    public String getLeadTrackingId() {
        return LeadTrackingId;
    }

    public void setLeadTrackingId(String leadTrackingId) {
        LeadTrackingId = leadTrackingId;
    }

    public LeadStatus getLeadStatus() {
        return leadStatus;
    }

    public void setLeadStatus(LeadStatus leadStatus) {
        this.leadStatus = leadStatus;
    }
}
