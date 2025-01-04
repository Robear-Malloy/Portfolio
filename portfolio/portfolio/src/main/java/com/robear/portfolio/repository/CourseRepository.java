package com.robear.portfolio.repository;

import com.robear.portfolio.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
    @Query("SELECT c FROM Course c WHERE c.educationId = :id")
    List<Course> findSchoolCourses(Long id);
}