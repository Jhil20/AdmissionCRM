package com.admissioncrm.authenticationservice.Repositories;

import com.admissioncrm.authenticationservice.Entities.Course;
import com.admissioncrm.authenticationservice.Entities.Institution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, String> {

    List<Course> findCourseByInstitutionId_Id(String institutionId);
//    List<Course> findAllByInstitutionId(Institution institution);
}

