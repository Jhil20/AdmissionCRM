package com.admissioncrm.authenticationservice.Repositories;

import com.admissioncrm.authenticationservice.Entities.Institution;
import com.admissioncrm.authenticationservice.Enums.CategoryType;
import com.admissioncrm.authenticationservice.Enums.DegreeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InstitutionRepository extends JpaRepository<Institution, String> {

    Optional<Institution> findByInstituteName(String instituteName);

    @Query("select i from Institution i join i.program p where i.type = :category AND p =:program")
    List<Institution> findByCategoryAndProgram(
            @Param("category") CategoryType category,
            @Param("program")DegreeType program
            );

}
