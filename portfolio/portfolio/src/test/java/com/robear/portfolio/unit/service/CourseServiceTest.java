//package com.robear.portfolio.unit.service;
//
//import com.robear.portfolio.model.Course;
//import com.robear.portfolio.repository.CourseRepository;
//import com.robear.portfolio.service.CourseService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import java.util.Collections;
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.mockito.Mockito.*;
//
//public class CourseServiceTest {
//
//    @InjectMocks
//    private CourseService courseService;
//
//    @Mock
//    private CourseRepository courseRepository;
//
//    private final Course course = new Course();
//
//    @BeforeEach
//    public void setup() {
//        MockitoAnnotations.openMocks(this);
//
//        course.setId(1L);
//        course.setEducationId(1L);
//        course.setName("Test Course");
//    }
//
//    @Test
//    public void testAddCourseWhenSuccessful() {
//        when(courseRepository.save(course)).thenReturn(course);
//
//        Course result = courseService.addCourse(course);
//
//        assertThat(result).isNotNull();
//        assertThat(result.getId()).isEqualTo(course.getId());
//        verify(courseRepository).save(course);
//    }
//
//    @Test
//    public void testAddCourseWhenThrowsException() {
//        when(courseRepository.save(course)).thenThrow(new RuntimeException("Error"));
//
//        try {
//            courseService.addCourse(course);
//        } catch (Exception e) {
//            assertThat(e).isInstanceOf(RuntimeException.class);
//            assertThat(e.getMessage()).isEqualTo("Error");
//        }
//
//        verify(courseRepository).save(course);
//    }
//
//    @Test
//    public void testGetAllCoursesWhenSuccessful() {
//        List<Course> courses = Collections.singletonList(course);
//        when(courseRepository.findAll()).thenReturn(courses);
//
//        List<Course> result = courseService.getAllCourses();
//
//        assertThat(result).isNotNull();
//        assertThat(result.size()).isEqualTo(1);
//        assertThat(result.get(0).getId()).isEqualTo(course.getId());
//        verify(courseRepository).findAll();
//    }
//
//    @Test
//    public void testGetAllCoursesWhenThrowsException() {
//        when(courseRepository.findAll()).thenThrow(new RuntimeException("Error"));
//
//        try {
//            courseService.getAllCourses();
//        } catch (Exception e) {
//            assertThat(e).isInstanceOf(RuntimeException.class);
//            assertThat(e.getMessage()).isEqualTo("Error");
//        }
//
//        verify(courseRepository).findAll();
//    }
//
//    @Test
//    public void testGetAllEducationCoursesWhenSuccessful() {
//        Long educationId = 1L;
//        List<Course> courses = Collections.singletonList(course);
//        when(courseRepository.findSchoolCourses(educationId)).thenReturn(courses);
//
//        List<Course> result = courseService.getAllEducationCourses(educationId);
//
//        assertThat(result).isNotNull();
//        assertThat(result.size()).isEqualTo(1);
//        assertThat(result.get(0).getId()).isEqualTo(course.getId());
//        verify(courseRepository).findSchoolCourses(educationId);
//    }
//
//    @Test
//    public void testGetAllEducationCoursesWhenThrowsException() {
//        Long educationId = 1L;
//        when(courseRepository.findSchoolCourses(educationId)).thenThrow(new RuntimeException("Error"));
//
//        try {
//            courseService.getAllEducationCourses(educationId);
//        } catch (Exception e) {
//            assertThat(e).isInstanceOf(RuntimeException.class);
//            assertThat(e.getMessage()).isEqualTo("Error");
//        }
//
//        verify(courseRepository).findSchoolCourses(educationId);
//    }
//
//    @Test
//    public void testDeleteCourseWhenSuccessful() {
//        Long courseId = 1L;
//
//        doNothing().when(courseRepository).deleteById(courseId);
//
//        courseService.deleteCourse(courseId);
//
//        verify(courseRepository).deleteById(courseId);
//    }
//
//    @Test
//    public void testDeleteCourseWhenThrowsException() {
//        Long courseId = 1L;
//
//        doThrow(new RuntimeException("Error")).when(courseRepository).deleteById(courseId);
//
//        try {
//            courseService.deleteCourse(courseId);
//        } catch (Exception e) {
//            assertThat(e).isInstanceOf(RuntimeException.class);
//            assertThat(e.getMessage()).isEqualTo("Error");
//        }
//
//        verify(courseRepository).deleteById(courseId);
//    }
//}
