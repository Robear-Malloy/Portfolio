package com.robear.portfolio.unit.controller;

import com.robear.portfolio.controller.ExperienceDescriptionController;
import com.robear.portfolio.exception.ExperienceDescriptionNotFoundException;
import com.robear.portfolio.model.ExperienceDescription;
import com.robear.portfolio.service.ExperienceDescriptionService;
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

public class ExperienceDescriptionControllerTest {
    private final ExperienceDescription description = new ExperienceDescription();

    @InjectMocks
    private ExperienceDescriptionController descriptionController;

    @Mock
    private ExperienceDescriptionService descriptionService;

    @BeforeEach
    public void setup() {
        description.setId(1L);
        description.setDescription("Test Description");
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddDescriptionWhenSuccessful() {
        when(descriptionService.addExperienceDescription(description))
                .thenReturn(description);

        ResponseEntity<ExperienceDescription> response = descriptionController
                .createExperienceDescription(description);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response).isNotNull();
        assertThat(response.getBody()).isEqualTo(description);
    }

    @Test
    public void testAddDescriptionWhenThrowsException() {
        when(descriptionService.addExperienceDescription(description))
                .thenThrow(new RuntimeException(""));

        ResponseEntity<ExperienceDescription> response =
                descriptionController.createExperienceDescription(description);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(response.getBody()).isNull();
    }

    @Test
    public void testGetDescriptionsWhenRecordsExist() {
        List<ExperienceDescription> descriptions = Collections.singletonList(description);
        when(descriptionService.getExperienceDescriptions(description.getId()))
                .thenReturn(descriptions);

        ResponseEntity<List<ExperienceDescription>> response =
                descriptionController.getAllExperienceDescriptionById(description.getId());

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody()).isEqualTo(descriptions);
    }

    @Test
    public void testGetDescriptionsWhenRecordDoesNotExist() {
        when(descriptionService.getExperienceDescriptions(description.getId()))
                .thenThrow(new ExperienceDescriptionNotFoundException(""));

        ResponseEntity<List<ExperienceDescription>> response =
                descriptionController.getAllExperienceDescriptionById(description.getId());

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody()).isNull();
    }

    @Test
    public void testGetDescriptionsWhenThrowsException() {
        when(descriptionService.getExperienceDescriptions(description.getId()))
                .thenThrow(new RuntimeException(""));

        ResponseEntity<List<ExperienceDescription>> response =
                descriptionController.getAllExperienceDescriptionById(description.getId());

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(response.getBody()).isNull();
    }

    @Test
    public void testUpdateDescriptionWhenSuccessful() {
        when(descriptionService.updateExperienceDescription(description))
                .thenReturn(description);

        ResponseEntity<ExperienceDescription> response =
                descriptionController.updateExperienceDescription(description);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody()).isEqualTo(description);
    }

    @Test
    public void testUpdateDescriptionWhenDoesNotExist() {
        when(descriptionService.updateExperienceDescription(description))
                .thenThrow(new ExperienceDescriptionNotFoundException(""));

        ResponseEntity<ExperienceDescription> response =
                descriptionController.updateExperienceDescription(description);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody()).isNull();
    }

    @Test
    public void testUpdateDescriptionWhenThrowsException() {
        when(descriptionService.updateExperienceDescription(description))
                .thenThrow(new RuntimeException());

        ResponseEntity<ExperienceDescription> response =
                descriptionController.updateExperienceDescription(description);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(response.getBody()).isNull();
    }

    @Test
    public void testDeleteDescriptionWhenSuccessful() {
        doNothing().when(descriptionService).deleteExperienceDescription(description);

        ResponseEntity<Void> response =
                descriptionController.deleteExperienceDescription(description);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        verify(descriptionService).deleteExperienceDescription(description);
    }

    @Test
    public void testDeleteDescriptionWhenDoesNotExist() {
        doThrow(new ExperienceDescriptionNotFoundException(""))
                .when(descriptionService).deleteExperienceDescription(description);

        ResponseEntity<Void> response =
                descriptionController.deleteExperienceDescription(description);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        verify(descriptionService).deleteExperienceDescription(description);
    }

    @Test
    public void testDeleteDescriptionWhenThrowsException() {
        doThrow(new RuntimeException()).when(descriptionService)
                .deleteExperienceDescription(description);

        ResponseEntity<Void> response =
                descriptionController.deleteExperienceDescription(description);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        verify(descriptionService).deleteExperienceDescription(description);
    }
}
