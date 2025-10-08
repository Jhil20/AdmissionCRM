package com.admissioncrm.authenticationservice.DTO.Courses;

import com.admissioncrm.authenticationservice.Enums.DegreeType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
public class InstituteFilterDTO {
    private String id;
    private String InstituteName;
    private List<DegreeType> program;

    public InstituteFilterDTO(){}

    public InstituteFilterDTO(String id, String instituteName, List<DegreeType> program) {
        this.id = id;
        InstituteName = instituteName;
        this.program = program;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInstituteName() {
        return InstituteName;
    }

    public void setInstituteName(String instituteName) {
        InstituteName = instituteName;
    }

    public List<DegreeType> getProgram() {
        return program;
    }

    public void setProgram(List<DegreeType> program) {
        this.program = program;
    }
}
