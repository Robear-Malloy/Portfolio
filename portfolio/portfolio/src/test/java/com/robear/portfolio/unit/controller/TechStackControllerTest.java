package com.robear.portfolio.unit.controller;

import com.robear.portfolio.controller.TechStackController;
import com.robear.portfolio.enums.TechType;
import com.robear.portfolio.exception.TechStackNotFoundException;
import com.robear.portfolio.exception.InvalidTechStackException;
import com.robear.portfolio.model.TechStack;
import com.robear.portfolio.service.TechStackService;
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

public class TechStackControllerTest {
    private final TechStack projectTechStack = new TechStack();
    private final TechStack experienceTechStack = new TechStack();

    @InjectMocks
    private TechStackController techStackController;

    @Mock
    private TechStackService techStackService;

    @BeforeEach
    public void setup() {
        projectTechStack.setId(1L);
        projectTechStack.setExperienceId(null);
        projectTechStack.setProjectId(1L);
        projectTechStack.setSkillId(1L);

        experienceTechStack.setId(2L);
        experienceTechStack.setExperienceId(1L);
        experienceTechStack.setProjectId(null);
        experienceTechStack.setSkillId(1L);
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddTechStackWhenSuccessful() {
        when(techStackService.createTechStack(projectTechStack))
                .thenReturn(projectTechStack);

        ResponseEntity<TechStack> response = techStackController
                .addTechStack(projectTechStack);

        assertThat(response.getStatusCode())
                .isEqualTo(HttpStatus.CREATED);
        assertThat(response).isNotNull();
        assertThat(response.getBody())
                .isEqualTo(projectTechStack);
    }

    @Test
    public void testAddTechStackWhenThrowsException() {
        when(techStackService.createTechStack(projectTechStack))
                .thenThrow(new RuntimeException(""));

        ResponseEntity<TechStack> response = techStackController
                .addTechStack(projectTechStack);

        assertThat(response.getStatusCode())
                .isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(response.getBody()).isNull();
    }

    @Test
    public void testGetProjectTechStackWhenSuccessful() {
        List<TechStack> techStackList = Collections.singletonList(projectTechStack);
        when(techStackService.getTechStack(projectTechStack.getId(), TechType.PROJECT))
                .thenReturn(techStackList);

        ResponseEntity<List<TechStack>> response = techStackController
                .getProjectTechStack(projectTechStack.getId());

        assertThat(response.getStatusCode())
                .isEqualTo(HttpStatus.OK);
        assertThat(response).isNotNull();
        assertThat(response.getBody())
                .isEqualTo(techStackList);
    }

    @Test
    public void testGetProjectTechStackWhenNotFound() {
        when(techStackService.getTechStack(projectTechStack.getId(), TechType.PROJECT))
                .thenThrow(new TechStackNotFoundException(""));

        ResponseEntity<List<TechStack>> response = techStackController
                .getProjectTechStack(projectTechStack.getId());

        assertThat(response.getStatusCode())
                .isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody()).isNull();
    }

    @Test
    public void testGetProjectTechStackWhenThrowsException() {
        when(techStackService.getTechStack(projectTechStack.getId(), TechType.PROJECT))
                .thenThrow(new RuntimeException(""));

        ResponseEntity<List<TechStack>> response = techStackController
                .getProjectTechStack(projectTechStack.getId());

        assertThat(response.getStatusCode())
                .isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(response.getBody()).isNull();
    }

    @Test
    public void testGetExperienceTechStackWhenSuccessful() {
        List<TechStack> techStackList = Collections.singletonList(experienceTechStack);
        when(techStackService.getTechStack(experienceTechStack.getId(), TechType.EXPERIENCE))
                .thenReturn(techStackList);

        ResponseEntity<List<TechStack>> response = techStackController
                .getExperienceTechStack(experienceTechStack.getId());

        assertThat(response.getStatusCode())
                .isEqualTo(HttpStatus.OK);
        assertThat(response).isNotNull();
        assertThat(response.getBody())
                .isEqualTo(techStackList);
    }

    @Test
    public void testGetExperienceTechStackWhenNotFound() {
        when(techStackService.getTechStack(experienceTechStack.getId(), TechType.EXPERIENCE))
                .thenThrow(new TechStackNotFoundException(""));

        ResponseEntity<List<TechStack>> response = techStackController
                .getExperienceTechStack(experienceTechStack.getId());

        assertThat(response.getStatusCode())
                .isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody()).isNull();
    }

    @Test
    public void testGetExperienceTechStackWhenThrowsException() {
        when(techStackService.getTechStack(experienceTechStack.getId(), TechType.EXPERIENCE))
                .thenThrow(new RuntimeException(""));

        ResponseEntity<List<TechStack>> response = techStackController
                .getExperienceTechStack(experienceTechStack.getId());

        assertThat(response.getStatusCode())
                .isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(response.getBody()).isNull();
    }

    @Test
    public void testDeleteTechStackWhenSuccessful() {
        doNothing().when(techStackService).deleteTechStack(projectTechStack.getId());

        ResponseEntity<Void> response =
                techStackController.deleteTechStack(projectTechStack.getId());

        assertThat(response.getStatusCode())
                .isEqualTo(HttpStatus.NO_CONTENT);
        verify(techStackService).deleteTechStack(projectTechStack.getId());
    }

    @Test
    public void testDeleteTechStackWhenNotFound() {
        doThrow(new TechStackNotFoundException("")).when(techStackService)
                .deleteTechStack(projectTechStack.getId());

        ResponseEntity<Void> response =
                techStackController.deleteTechStack(projectTechStack.getId());

        assertThat(response.getStatusCode())
                .isEqualTo(HttpStatus.NOT_FOUND);
        verify(techStackService).deleteTechStack(projectTechStack.getId());
    }

    @Test
    public void testDeleteTechStackWhenThrowsException() {
        doThrow(new RuntimeException("")).when(techStackService)
                .deleteTechStack(projectTechStack.getId());

        ResponseEntity<Void> response =
                techStackController.deleteTechStack(projectTechStack.getId());

        assertThat(response.getStatusCode())
                .isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        verify(techStackService).deleteTechStack(projectTechStack.getId());
    }
}
