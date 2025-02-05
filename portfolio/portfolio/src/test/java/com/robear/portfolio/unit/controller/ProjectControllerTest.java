package com.robear.portfolio.unit.controller;

import com.robear.portfolio.controller.ProjectController;
import com.robear.portfolio.exception.ProjectNotFoundException;
import com.robear.portfolio.model.Project;
import com.robear.portfolio.service.ProjectService;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
public class ProjectControllerTest {
    @InjectMocks
    private ProjectController projectController;

    @Mock
    private ProjectService projectService;

    @BeforeEach
    public void setup() { MockitoAnnotations.openMocks(this); }

    @Test
    public void testWhenNewProjectCreates() {
        Project project = createTestProject();
        when(projectService.addProject(any(Project.class))).thenReturn(project);

        ResponseEntity<Project> response =
                projectController.createProject(project);

        assertThat(response.getStatusCode())
                .isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getId())
                .isEqualTo(project.getId());
    }

    @Test
    public void testNewProjectCreatedWhenThrowsException() {
        Project project = createTestProject();
        when(projectService.addProject(any(Project.class))).thenThrow(new RuntimeException("Service Error"));

        ResponseEntity<Project> response =
                projectController.createProject(project);

        assertThat(response.getStatusCode())
                .isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(response.getBody()).isNull();
    }

    @Test
    public void testGetAllProjectsWhenProjectExists() {
        Project project = createTestProject();
        List<Project> projects = Collections.singletonList(project);
        when(projectService.getAllProjects("en")).thenReturn(projects);

        ResponseEntity<List<Project>> response =
                projectController.getAllProjects("en");

        assertThat(response.getStatusCode())
                .isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getFirst().getId())
                .isEqualTo(projects.getFirst().getId());
    }

    @Test
    public void testGetAllProjectsWhenProjectDoesNotExist() {
        when(projectService.getAllProjects("en"))
                .thenThrow(new ProjectNotFoundException(""));

        ResponseEntity<List<Project>> response =
                projectController.getAllProjects("en");

        assertThat(response.getStatusCode())
                .isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody()).isNull();
    }

    @Test
    public void testGetAllProjectsWhenThrowsException() {
        when(projectService.getAllProjects("en"))
                .thenThrow(new RuntimeException(""));

        ResponseEntity<List<Project>> response =
                projectController.getAllProjects("en");

        assertThat(response.getStatusCode())
                .isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(response.getBody()).isNull();
    }

    @Test
    public void testGetProjectByIdWhenProjectExists() {
        Project project = createTestProject();
        when(projectService.getProjectById(project.getId()))
                .thenReturn(project);

        ResponseEntity<Project> response =
                projectController.getProjectById(project.getId());

        assertThat(response.getStatusCode())
                .isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getId())
                .isEqualTo(project.getId());
    }

    @Test
    public void testGetProjectByIdWhenProjectDoesNotExist() {
        when(projectService.getProjectById(1L))
                .thenThrow(new ProjectNotFoundException(""));

        ResponseEntity<Project> response =
                projectController.getProjectById(1L);

        assertThat(response.getStatusCode())
                .isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody()).isNull();
    }

    @Test
    public void testGetProjectByIdWhenThrowsException() {
        when(projectService.getProjectById(1L))
                .thenThrow(new RuntimeException(""));

        ResponseEntity<Project> response =
                projectController.getProjectById(1L);

        assertThat(response.getStatusCode())
                .isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(response.getBody()).isNull();
    }

    @Test
    public void testGetFeaturedProjectsWhenProjectExists() {
        Project project = createTestProject();
        List<Project> projects = Collections.singletonList(project);
        when(projectService.getFeaturedProjects("en")).thenReturn(projects); // pass "en" as lang

        ResponseEntity<List<Project>> response =
                projectController.getFeaturedProjects("en"); // pass "en" as lang

        assertThat(response.getStatusCode())
                .isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().get(0).getId()) // Corrected method to fetch by index
                .isEqualTo(projects.get(0).getId());
    }


    @Test
    public void testGetFeaturedProjectsWhenProjectDoesNotExist() {
        when(projectService.getFeaturedProjects("en"))
                .thenThrow(new ProjectNotFoundException(""));

        ResponseEntity<List<Project>> response =
                projectController.getFeaturedProjects("en"); // pass "en" as lang

        assertThat(response.getStatusCode())
                .isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody()).isNull();
    }


    @Test
    public void testGetFeaturedProjectsWhenThrowsException() {
        when(projectService.getFeaturedProjects("en"))
                .thenThrow(new RuntimeException(""));

        ResponseEntity<List<Project>> response =
                projectController.getFeaturedProjects("en");

        assertThat(response.getStatusCode())
                .isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(response.getBody()).isNull();
    }

    @Test
    public void testDeleteProjectByIdWhenProjectExists() {
        doNothing().when(projectService).deleteProject(1L);

        ResponseEntity<Void> response =
                projectController.deleteProjectById(1L);

        assertThat(response.getStatusCode())
                .isEqualTo(HttpStatus.NO_CONTENT);
        verify(projectService).deleteProject(1L);
    }

    @Test
    public void testDeleteProjectByIdWhenProjectDoesNotExist() {
        doThrow(new ProjectNotFoundException(""))
                .when(projectService).deleteProject(1L);

        ResponseEntity<Void> response =
                projectController.deleteProjectById(1L);

        assertThat(response.getStatusCode())
                .isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody()).isNull();
    }

    @Test
    public void testDeleteProjectByIdWhenThrowsException() {
        doThrow(new RuntimeException(""))
                .when(projectService).deleteProject(1L);

        ResponseEntity<Void> response =
                projectController.deleteProjectById(1L);

        assertThat(response.getStatusCode())
                .isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(response.getBody()).isNull();
    }

    @Test
    public void testUpdateProjectByIdWhenRecordExists() {
        Project project = createTestProject();

        when(projectService.updateProject(project.getId(), project))
                .thenReturn(project);

        ResponseEntity<Project> response =
                projectController.updateProjectById(project.getId(), project);

        assertThat(response.getStatusCode())
                .isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getTitle())
                .isEqualTo(project.getTitle());
    }

    @Test
    public void testUpdateProjectByIdWhenRecordDoesNotExist() {
        Project project = createTestProject();
        when(projectService.updateProject(1L, project))
                .thenThrow(new ProjectNotFoundException(""));

        ResponseEntity<Project> response =
                projectController.updateProjectById(1L, project);

        assertThat(response.getStatusCode())
                .isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody()).isNull();
    }

    @Test
    public void testUpdateProjectByIdWhenThrowsException() {
        Project project = createTestProject();
        when(projectService.updateProject(1L, project))
                .thenThrow(new RuntimeException(""));

        ResponseEntity<Project> response =
                projectController.updateProjectById(1L, project);

        assertThat(response.getStatusCode())
                .isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(response.getBody()).isNull();
    }

    @Test
    public void testSetProjectFeaturedWhenProjectExists() {
        Project project = createTestProject();
        when(projectService.setFeatured(project.getId(), project.getIsFeatured()))
                .thenReturn(project);

        ResponseEntity<Project> response =
                projectController.setProjectIsFeatured(project.getId(), project.getIsFeatured());

        assertThat(response.getStatusCode())
                .isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getIsFeatured())
                .isEqualTo(project.getIsFeatured());
    }

    @Test
    public void testSetProjectFeaturedWhenProjectDoesNotExist() {
        when(projectService.setFeatured(1L, true))
                .thenThrow(new ProjectNotFoundException(""));

        ResponseEntity<Project> response =
                projectController.setProjectIsFeatured(1L, true);

        assertThat(response.getStatusCode())
                .isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody()).isNull();
    }

    @Test
    public void testSetProjectFeaturedWhenThrowsException() {
        when(projectService.setFeatured(1L, true))
                .thenThrow(new RuntimeException(""));

        ResponseEntity<Project> response =
                projectController.setProjectIsFeatured(1L, true);

        assertThat(response.getStatusCode())
                .isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(response.getBody()).isNull();
    }

    private Project createTestProject() {
        Project project = new Project();

        project.setId(1L);
        project.setTitle("Test project");
        project.setDescription("Test Description");
        project.setPhotoFile("C:/testFile");
        project.setRepoLink("github.com/test");
        project.setDescription("youtube.com/test");
        project.setIsFeatured(true);

        return project;
    }


}
