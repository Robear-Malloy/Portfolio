package com.robear.portfolio.unit.controller;

import com.robear.portfolio.controller.CourseController;
import com.robear.portfolio.model.Course;
import com.robear.portfolio.service.CourseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class CourseControllerTest {
    @InjectMocks
    private CourseController courseController;

    @Mock
    private CourseService courseService;

    private final Course course = new Course();

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);

        course.setId(1L);
        course.setEducationId(1L);
        course.setName("Test Course");
        course.setLanguage("en");
    }

    @Test
    public void testCreateCourseWhenSuccessful() {
        when(courseService.addCourse(course)).thenReturn(course);

        ResponseEntity<Course> response = courseController.createCourse(course);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getId()).isEqualTo(course.getId());
        verify(courseService).addCourse(course);
    }

    @Test
    public void testCreateCourseWhenThrowsException() {
        when(courseService.addCourse(course)).thenThrow(new RuntimeException("Error"));

        ResponseEntity<Course> response = courseController.createCourse(course);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        verify(courseService).addCourse(course);
    }

    @Test
    public void testGetAllCoursesWhenSuccessful() {
        List<Course> courses = Collections.singletonList(course);
        when(courseService.getAllCourses(course.getLanguage())).thenReturn(courses);

        ResponseEntity<List<Course>> response = courseController.getAllCourses(course.getLanguage());

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().get(0).getId()).isEqualTo(course.getId());
        verify(courseService).getAllCourses(course.getLanguage());
    }

    @Test
    public void testGetAllCoursesWhenThrowsException() {
        when(courseService.getAllCourses(course.getLanguage())).thenThrow(new RuntimeException("Error"));

        ResponseEntity<List<Course>> response = courseController.getAllCourses(course.getLanguage());

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        verify(courseService).getAllCourses(course.getLanguage());
    }

    @Test
    public void testGetAllCoursesForSchoolWhenSuccessful() {
        List<Course> courses = Collections.singletonList(course);
        Long educationId = 1L;
        when(courseService.getAllEducationCourses(course.getLanguage(), educationId)).thenReturn(courses);

        ResponseEntity<List<Course>> response = courseController.getAllCoursesForSchool(course.getLanguage(), educationId);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().get(0).getId()).isEqualTo(course.getId());
        verify(courseService).getAllEducationCourses(course.getLanguage(), educationId);
    }

    @Test
    public void testGetAllCoursesForSchoolWhenThrowsException() {
        Long educationId = 1L;
        when(courseService.getAllEducationCourses(course.getLanguage(), educationId)).thenThrow(new RuntimeException("Error"));

        ResponseEntity<List<Course>> response = courseController.getAllCoursesForSchool(course.getLanguage(), educationId);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        verify(courseService).getAllEducationCourses(course.getLanguage(), educationId);
    }

    @Test
    public void testDeleteCourseWhenSuccessful() {
        Long courseId = 1L;

        doNothing().when(courseService).deleteCourse(courseId);

        ResponseEntity<Void> response = courseController.deleteCourse(courseId);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        verify(courseService).deleteCourse(courseId);
    }

    @Test
    public void testDeleteCourseWhenThrowsException() {
        Long courseId = 1L;

        doThrow(new RuntimeException("Error")).when(courseService).deleteCourse(courseId);

        ResponseEntity<Void> response = courseController.deleteCourse(courseId);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        verify(courseService).deleteCourse(courseId);
    }
}
