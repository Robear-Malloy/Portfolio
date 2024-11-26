package com.robear.portfolio.unit.controller;

import com.robear.portfolio.controller.EducationController;
import com.robear.portfolio.exception.EducationNotFoundException;
import com.robear.portfolio.model.Education;
import com.robear.portfolio.service.EducationService;
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

public class EducationControllerTest {
    private final Education education = new Education();

    @InjectMocks
    private EducationController educationController;

    @Mock
    private EducationService educationService;

    @BeforeEach
    public void setup() {
        education.setId(1L);
        education.setSchool("Test University");
        education.setDegree("4.0");
        education.setGpa(4.0f);
        education.setDateStarted("12-01-20");
        education.setDateEnded("12-01-20");
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddExperienceWhenSuccessful() {
        when(educationService.addEducation(education))
                .thenReturn(education);

        ResponseEntity<Education> response = educationController
                .createEducation(education);

        assertThat(response.getStatusCode())
                .isEqualTo(HttpStatus.CREATED);
        assertThat(response).isNotNull();
        assertThat(response.getBody())
                .isEqualTo(education);
    }

    @Test
    public void testAddExperienceWhenThrowsException() {
        when(educationService.addEducation(education))
                .thenThrow(new RuntimeException("Error"));

        ResponseEntity<Education> response = educationController
                .createEducation(education);

        assertThat(response.getStatusCode())
                .isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(response.getBody()).isNull();
    }

    @Test
    public void testGetAllEducationWhenRecordsExist() {
        List<Education> educationList = Collections.singletonList(education);
        when(educationService.getAllEducation())
                .thenReturn(educationList);

        ResponseEntity<List<Education>> response =
                educationController.getAllEducation();

        assertThat(response.getStatusCode())
                .isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody())
                .isEqualTo(educationList);
    }

    @Test
    public void testGetAllEducationWhenRecordDoesNotExist() {
        when(educationService.getAllEducation())
                .thenThrow(new EducationNotFoundException(""));

        ResponseEntity<List<Education>> response =
                educationController.getAllEducation();

        assertThat(response.getStatusCode())
                .isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody()).isNull();
    }

    @Test
    public void testGetAllEducationWhenThrowsException() {
        when(educationService.getAllEducation())
                .thenThrow(new RuntimeException(""));

        ResponseEntity<List<Education>> response =
                educationController.getAllEducation();

        assertThat(response.getStatusCode())
                .isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(response.getBody()).isNull();
    }

    @Test
    public void testGetEducationByIdWhenRecordExists() {
        when(educationService.getEducationById(education.getId()))
                .thenReturn(education);

        ResponseEntity<Education> response =
                educationController.getEducationById(education.getId());

        assertThat(response.getStatusCode())
                .isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody())
                .isEqualTo(education);
    }

    @Test
    public void testGetEducationByIdWhenRecordDoesNotExist() {
        when(educationService.getEducationById(education.getId()))
                .thenThrow(new EducationNotFoundException(""));

        ResponseEntity<Education> response =
                educationController.getEducationById(education.getId());

        assertThat(response.getStatusCode())
                .isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody())
                .isNull();
    }

    @Test
    public void testGetEducationByIdWhenThrowsException() {
        when(educationService.getEducationById(education.getId()))
                .thenThrow(new RuntimeException(""));

        ResponseEntity<Education> response =
                educationController.getEducationById(education.getId());

        assertThat(response.getStatusCode())
                .isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(response.getBody())
                .isNull();
    }

    @Test
    public void testUpdateEducationByIdWhenSuccessful() {
        when(educationService.updateEducationById(education.getId(), education))
                .thenReturn(education);

        ResponseEntity<Education> response =
                educationController.updateEducationById(education.getId(), education);

        assertThat(response.getStatusCode())
                .isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody())
                .isEqualTo(education);
    }

    @Test
    public void testUpdateEducationByIdWhenNoneFound() {
        when(educationService.updateEducationById(education.getId(), education))
                .thenThrow(new EducationNotFoundException(""));

        ResponseEntity<Education> response =
                educationController.updateEducationById(education.getId(), education);

        assertThat(response.getStatusCode())
                .isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody()).isNull();
    }

    @Test
    public void testUpdateEducationByIdWhenThrowsException() {
        when(educationService.updateEducationById(education.getId(), education))
                .thenThrow(new RuntimeException(""));

        ResponseEntity<Education> response =
                educationController.updateEducationById(education.getId(), education);

        assertThat(response.getStatusCode())
                .isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(response.getBody()).isNull();
    }

    @Test
    public void testUpdateGpaByIdWhenSuccessful() {
        when(educationService.updateGpaById(education.getId(), education.getGpa()))
                .thenReturn(education);

        ResponseEntity<Education> response =
                educationController.updateGpaById(education.getId(), education.getGpa());

        assertThat(response.getStatusCode())
                .isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody())
                .isEqualTo(education);
    }

    @Test
    public void testUpdateGpaByIdWhenNoRecordFound() {
        when(educationService.updateGpaById(education.getId(), education.getGpa()))
                .thenThrow(new EducationNotFoundException(""));

        ResponseEntity<Education> response =
                educationController.updateGpaById(education.getId(), education.getGpa());

        assertThat(response.getStatusCode())
                .isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody()).isNull();
    }

    @Test
    public void testUpdateGpaByIdWhenThrowsException() {
        when(educationService.updateGpaById(education.getId(), education.getGpa()))
                .thenThrow(new RuntimeException(""));

        ResponseEntity<Education> response =
                educationController.updateGpaById(education.getId(), education.getGpa());

        assertThat(response.getStatusCode())
                .isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(response.getBody()).isNull();
    }

    @Test
    public void testDeleteEducationWhenSuccessful() {
        doNothing().when(educationService).deleteEducation(education.getId());

        ResponseEntity<Void> response =
                educationController.deleteEducation(education.getId());

        assertThat(response.getStatusCode())
                .isEqualTo(HttpStatus.NO_CONTENT);
        verify(educationService).deleteEducation(education.getId());
    }

    @Test
    public void testDeleteEducationWhenNoRecordFound() {
        doThrow(new EducationNotFoundException("")).when(educationService)
                .deleteEducation(education.getId());

        ResponseEntity<Void> response =
                educationController.deleteEducation(education.getId());

        assertThat(response.getStatusCode())
                .isEqualTo(HttpStatus.NOT_FOUND);
        verify(educationService).deleteEducation(education.getId());
    }

    @Test
    public void testDeleteEducationWhenThrowsException() {
        doThrow(new RuntimeException("")).when(educationService)
                .deleteEducation(education.getId());

        ResponseEntity<Void> response =
                educationController.deleteEducation(education.getId());

        assertThat(response.getStatusCode())
                .isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        verify(educationService).deleteEducation(education.getId());
    }
}
