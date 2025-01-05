package com.robear.portfolio.controller.interfaces;

import com.robear.portfolio.model.Course;
import org.springframework.http.ResponseEntity;
import java.util.List;

public interface ICourseController {
    ResponseEntity<Course> createCourse(Course course);
    ResponseEntity<List<Course>> getAllCourses();
    ResponseEntity<List<Course>> getAllCoursesForSchool(Long educationId);
    ResponseEntity<Void> deleteCourse(Long id);
}
