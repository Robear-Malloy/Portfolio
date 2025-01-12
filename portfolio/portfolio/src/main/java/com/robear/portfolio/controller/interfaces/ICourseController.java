package com.robear.portfolio.controller.interfaces;

import com.robear.portfolio.model.Course;
import org.springframework.http.ResponseEntity;
import java.util.List;

public interface ICourseController {
    ResponseEntity<Course> createCourse(Course course);
    ResponseEntity<List<Course>> getAllCourses(String lang);
    ResponseEntity<List<Course>> getAllCoursesForSchool(String lang, Long educationId);
    ResponseEntity<Void> deleteCourse(Long id);
}
