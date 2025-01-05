package com.robear.portfolio.service.interfaces;

import com.robear.portfolio.model.Course;
import java.util.List;

public interface ICourseService {
    Course addCourse(Course course);
    List<Course> getAllCourses();
    List<Course> getAllEducationCourses(Long educationId);
    void deleteCourse(Long id);
}
