package com.robear.portfolio.repository;

import com.robear.portfolio.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
    @Query("SELECT c FROM Course c WHERE c.language = :language AND c.educationId = :id")
    List<Course> findSchoolCourses(String language, Long id);
    @Query("SELECT c FROM Course c WHERE c.language = :language")
    List<Course> findAllByLanguage(String language);
}