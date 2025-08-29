package com.admission_crm.lead_management.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;



@Data
public class LeadRequest {
    private String userId;

    @NotBlank(message = "Institute name is required")
    @Size(max = 100, message = "Institute name must not exceed 100 characters")
    private String instituteName;

    @NotBlank(message = "Subject is required")
    @Size(max = 50, message = "Subject must not exceed 50 characters")
    private String subject;

    @NotBlank(message = "Course interested is required")
    @Size(max = 100, message = "Course interested must not exceed 100 characters")
    private String courseInterested;

    // Optional field, no validation annotations for requiredness
    @Size(max = 50, message = "Budget range must not exceed 50 characters")
    private String budgetRange;

    @NotBlank(message = "Description is required")
    @Size(max = 500, message = "Description must not exceed 500 characters")
    private String description;

    @NotBlank(message = "Qualification is required")
    @Size(max = 100, message = "Qualification must not exceed 100 characters")
    private String qualification;
}
