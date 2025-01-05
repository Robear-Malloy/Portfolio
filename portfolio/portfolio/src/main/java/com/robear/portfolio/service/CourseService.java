package com.robear.portfolio.service;

import com.robear.portfolio.model.Course;
import com.robear.portfolio.repository.CourseRepository;
import com.robear.portfolio.service.interfaces.ICourseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService implements ICourseService {
    private static Logger logger = LoggerFactory.getLogger(CourseService.class);
    private final CourseRepository courseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public Course addCourse(Course course) {
        try {
            logger.info("Adding Course to Database: {}", course);
            return courseRepository.save(course);
        } catch (Exception e) {
            logger.error("Error attempting to add course to database.");
            throw e;
        }
    }

    @Cacheable(value="courses")
    @Override
    public List<Course> getAllCourses() {
        try {
            logger.info("Getting All Courses from Database");
            return courseRepository.findAll();
        } catch (Exception e) {
            logger.error("Error getting all courses from database");
            throw e;
        }
    }

    @Cacheable(value="courses")
    @Override
    public List<Course> getAllEducationCourses(Long educationId) {
        try {
            logger.info("Get all courses for education: {}", educationId);
            return courseRepository.findSchoolCourses(educationId);
        } catch (Exception e) {
            logger.error("Error getting all courses for associated education");
            throw e;
        }
    }

    @Override
    public void deleteCourse(Long id) {
        try {
            logger.info("Deleting course: {}", id);
            courseRepository.deleteById(id);
        } catch (Exception e) {
            logger.error("Error deleting course");
            throw e;
        }
    }
}
