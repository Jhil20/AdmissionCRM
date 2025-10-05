package com.admissioncrm.authenticationservice.Repositories;

import com.admissioncrm.authenticationservice.Entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CourseRepository extends JpaRepository<Course, String> {

}
