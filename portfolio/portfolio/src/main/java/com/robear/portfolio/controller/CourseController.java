package com.robear.portfolio.controller;

import com.robear.portfolio.controller.interfaces.ICourseController;
import com.robear.portfolio.model.Course;
import com.robear.portfolio.service.CourseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/course")
public class CourseController implements ICourseController {
    private static final Logger logger = LoggerFactory.getLogger(CourseController.class);

    @Autowired
    private CourseService courseService;

    @PostMapping
    @Override
    public ResponseEntity<Course> createCourse(
            @RequestBody Course course) {
        try {
            logger.info("Creating a new course {}", course);
            Course result = courseService.addCourse(course);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error creating course. {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping()
    @Override
    public ResponseEntity<List<Course>> getAllCourses(
            @RequestParam(value = "lang", required = false, defaultValue = "en") String lang) {
        try {
            logger.info("Getting all courses");
            List<Course> result = courseService.getAllCourses(lang);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error getting all courses. {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{educationId}")
    @Override
    public ResponseEntity<List<Course>> getAllCoursesForSchool(
            @RequestParam(value = "lang", required = false, defaultValue = "en") String lang,
            @PathVariable Long educationId) {
        try {
            logger.info("Getting all courses associated with education id: {}", educationId);
            List<Course> result = courseService.getAllEducationCourses(lang, educationId);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error getting all courses for school. {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<Void> deleteCourse(
            @PathVariable Long id) {
        try {
            logger.info("Deleting course: {}", id);
            courseService.deleteCourse(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            logger.error("Error deleting course. {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
