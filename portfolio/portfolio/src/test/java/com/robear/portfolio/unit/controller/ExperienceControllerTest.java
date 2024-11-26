package com.robear.portfolio.unit.controller;

import com.robear.portfolio.controller.ExperienceController;
import com.robear.portfolio.exception.ExperienceNotFoundException;
import com.robear.portfolio.model.Experience;
import com.robear.portfolio.service.ExperienceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class ExperienceControllerTest {
    private final Experience experience = new Experience();

    @InjectMocks
    private ExperienceController experienceController;

    @Mock
    private ExperienceService experienceService;

    @BeforeEach
    public void setup() {
        experience.setId(1L);
        experience.setCompany("Test Company");
        experience.setPosition("Tester");
        experience.setDateStarted(LocalDate.now().toString());
        experience.setDateEnded(LocalDate.now().toString());
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddExperienceWhenSuccessful() {
        when(experienceService.addExperience(experience))
                .thenReturn(experience);

        ResponseEntity<Experience> response = experienceController
                .createExperience(experience);

        assertThat(response.getStatusCode())
                .isEqualTo(HttpStatus.CREATED);
        assertThat(response).isNotNull();
        assertThat(response.getBody())
                .isEqualTo(experience);
    }

    @Test
    public void testAddExperienceWhenThrowsException() {
        when(experienceService.addExperience(experience))
                .thenThrow(new RuntimeException(""));

        ResponseEntity<Experience> response =
                experienceController.createExperience(experience);

        assertThat(response.getStatusCode())
                .isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(response.getBody()).isNull();
    }

    @Test
    public void testGetAllExperiencesWhenRecordsExist() {
        List<Experience> experiences = Collections.singletonList(experience);
        when(experienceService.getAllExperiences())
                .thenReturn(experiences);

        ResponseEntity<List<Experience>> response =
                experienceController.getAllExperiences();

        assertThat(response.getStatusCode())
                .isEqualTo(HttpStatus.OK);
        assertThat(response.getBody())
                .isNotNull();
        assertThat(response.getBody())
                .isEqualTo(experiences);
    }

    @Test
    public void testGetAllExperiencesWhenRecordDoesNotExist() {
        when(experienceService.getAllExperiences())
                .thenThrow(new ExperienceNotFoundException(""));

        ResponseEntity<List<Experience>> response =
                experienceController.getAllExperiences();

        assertThat(response.getStatusCode())
                .isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody()).isNull();
    }

    @Test
    public void testGetAllExperiencesWhenThrowsException() {
        when(experienceService.getAllExperiences())
                .thenThrow(new RuntimeException(""));

        ResponseEntity<List<Experience>> response =
                experienceController.getAllExperiences();

        assertThat(response.getStatusCode())
                .isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(response.getBody()).isNull();
    }

    @Test
    public void testGetExperienceByIdWhenSuccessful() {
        when(experienceService.getExperienceById(experience.getId()))
                .thenReturn(experience);

        ResponseEntity<Experience> response =
                experienceController.getExperienceById(experience.getId());

        assertThat(response.getStatusCode())
                .isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody())
                .isEqualTo(experience);
    }

    @Test
    public void testGetExperienceByIdWhenDoesNotExist() {
        when(experienceService.getExperienceById(experience.getId()))
                .thenThrow(new ExperienceNotFoundException(""));

        ResponseEntity<Experience> response =
            experienceController.getExperienceById(experience.getId());

        assertThat(response.getStatusCode())
                .isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody()).isNull();
    }

    @Test
    public void testGetExperienceByIdWhenThrowsException() {
        when(experienceService.getExperienceById(experience.getId()))
                .thenThrow(new RuntimeException(""));

        ResponseEntity<Experience> response =
                experienceController.getExperienceById(experience.getId());

        assertThat(response.getStatusCode())
                .isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(response.getBody()).isNull();
    }

    @Test
    public void testUpdateExperienceWhenSuccessful() {
        when(experienceService.updateExperience(experience.getId(), experience))
                .thenReturn(experience);

        ResponseEntity<Experience> response =
                experienceController.updateExperienceById(experience.getId(), experience);

        assertThat(response.getStatusCode())
                .isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody())
                .isEqualTo(experience);
    }

    @Test
    public void testUpdateExperienceWhenDoesNotExist() {
        when(experienceService.updateExperience(experience.getId(), experience))
                .thenThrow(new ExperienceNotFoundException(""));

        ResponseEntity<Experience> response =
                experienceController.updateExperienceById(experience.getId(), experience);

        assertThat(response.getStatusCode())
                .isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody()).isNull();
    }

    @Test
    public void testUpdateExperienceWhenThrowsException() {
        when(experienceService.updateExperience(experience.getId(), experience))
                .thenThrow(new RuntimeException());

        ResponseEntity<Experience> response =
                experienceController.updateExperienceById(experience.getId(), experience);

        assertThat(response.getStatusCode())
                .isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(response.getBody()).isNull();
    }

    @Test
    public void testDeleteExperienceWhenSuccessful() {
        doNothing().when(experienceService).deleteExperience(experience.getId());

        ResponseEntity<Void> response =
                experienceController.deleteExperienceById(experience.getId());

        assertThat(response.getStatusCode())
                .isEqualTo(HttpStatus.NO_CONTENT);
        verify(experienceService).deleteExperience(experience.getId());
    }

    @Test
    public void testDeleteExperienceWhenDoesNotExist() {
        doThrow(new ExperienceNotFoundException("")).when(experienceService)
                .deleteExperience(experience.getId());

        ResponseEntity<Void> response =
                experienceController.deleteExperienceById(experience.getId());

        assertThat(response.getStatusCode())
                .isEqualTo(HttpStatus.NOT_FOUND);
        verify(experienceService).deleteExperience(experience.getId());
    }

    @Test
    public void testDeleteExperienceWhenThrowsException() {
        doThrow(new RuntimeException()).when(experienceService)
                .deleteExperience(experience.getId());

        ResponseEntity<Void> response =
                experienceController.deleteExperienceById(experience.getId());

        assertThat(response.getStatusCode())
                .isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        verify(experienceService).deleteExperience(experience.getId());
    }
}
