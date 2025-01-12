package com.robear.portfolio.service.interfaces;

import com.robear.portfolio.model.Course;
import java.util.List;

public interface ICourseService {
    Course addCourse(Course course);
    List<Course> getAllCourses(String lang);
    List<Course> getAllEducationCourses(String lang, Long educationId);
    void deleteCourse(Long id);
}
