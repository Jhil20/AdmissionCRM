package org.example.leadmanagement.DTO.AdmissionInquiry.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdmissionInquiryCreateRes {

    private String admissionInquiryId;   // ID of the admission inquiry
    private String instituteName;      // Name of the institute
    private String instituteId;

    public String getAdmissionInquiryId() {
        return admissionInquiryId;
    }

    public void setAdmissionInquiryId(String admissionInquiryId) {
        this.admissionInquiryId = admissionInquiryId;
    }

    public String getInstituteName() {
        return instituteName;
    }

    public void setInstituteName(String instituteName) {
        this.instituteName = instituteName;
    }

    public String getInstituteId() {
        return instituteId;
    }

    public void setInstituteId(String instituteId) {
        this.instituteId = instituteId;
    }
}
