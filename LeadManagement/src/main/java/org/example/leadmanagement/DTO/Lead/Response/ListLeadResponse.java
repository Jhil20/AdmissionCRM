package org.example.leadmanagement.DTO.Lead.Response;

import java.time.LocalDateTime;
import java.util.Date;

public class ListLeadResponse {

    private String LeadId;
    private String StudentName;
    private String LeadSubject;
    private String LeadStatus;
    private String assignedTo;
    private String instituteName;
    private LocalDateTime date;
    private String LeadDescription;

    public ListLeadResponse(String leadId, String studentName, String leadSubject, String leadStatus, String assignedTo, String instituteName, LocalDateTime date, String leadDescription) {
        LeadId = leadId;
        StudentName = studentName;
        LeadSubject = leadSubject;
        LeadStatus = leadStatus;
        this.assignedTo = assignedTo;
        this.instituteName = instituteName;
        this.date = date;
        LeadDescription = leadDescription;
    }

    public ListLeadResponse() {}

    public String getLeadId() {
        return LeadId;
    }

    public void setLeadId(String leadId) {
        LeadId = leadId;
    }

    public String getStudentName() {
        return StudentName;
    }

    public void setStudentName(String studentName) {
        StudentName = studentName;
    }

    public String getLeadSubject() {
        return LeadSubject;
    }

    public void setLeadSubject(String leadSubject) {
        LeadSubject = leadSubject;
    }

    public String getLeadStatus() {
        return LeadStatus;
    }

    public void setLeadStatus(String leadStatus) {
        LeadStatus = leadStatus;
    }

    public String getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(String assignedTo) {
        this.assignedTo = assignedTo;
    }

    public String getInstituteName() {
        return instituteName;
    }

    public void setInstituteName(String instituteName) {
        this.instituteName = instituteName;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getLeadDescription() {
        return LeadDescription;
    }

    public void setLeadDescription(String leadDescription) {
        LeadDescription = leadDescription;
    }
}
