package org.example.leadmanagement.DTO.AdmissionInquiry.Response;

public class InstituteEnquiryDTO {
    private String instituteId;
    private String instituteName;

    public InstituteEnquiryDTO() {}

    public InstituteEnquiryDTO(String instituteId, String instituteName) {
        this.instituteId = instituteId;
        this.instituteName = instituteName;
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
}
