package com.robear.portfolio.unit.service;

import com.robear.portfolio.service.ProjectService;
import com.robear.portfolio.exception.ProjectNotFoundException;
import com.robear.portfolio.model.Project;
import com.robear.portfolio.repository.ProjectRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class ProjectServiceTest {
    @InjectMocks
    ProjectService projectService;

    private final Project existingProject = new Project();

    @Mock
    ProjectRepository projectRepository;


    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);

        existingProject.setId(1L);
        existingProject.setTitle("Test project");
        existingProject.setDescription("Test Description");
        existingProject.setPhotoFile("C:/testFile");
        existingProject.setRepoLink("github.com/test");
        existingProject.setDescription("youtube.com/test");
        existingProject.setIsFeatured(true);
    }

    @Test
    public void testAddProjectWhenProjectValid() {
        when(projectRepository.save(existingProject)).thenReturn(existingProject);

        Project result = projectRepository.save(existingProject);

        assertThat(result).isNotNull();
        assertThat(result.getId())
                .isEqualTo(existingProject.getId());
        verify(projectRepository).save(existingProject);
    }

    @Test
    public void testAddProjectWhenThrowsException() {
        when(projectRepository.save(existingProject))
                .thenThrow(new RuntimeException("Database Error"));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            projectService.addProject(existingProject);
        });

        assertThat(exception.getMessage())
                .isEqualTo("Unable to add project");
        verify(projectRepository).save(existingProject);
    }

    @Test
    public void testGetAllSkillsWhenSuccessful() {
        List<Project> projects = Collections.singletonList(existingProject);
        when(projectRepository.findAll()).thenReturn(projects);

        List<Project> result = projectService.getAllProjects();

        assertThat(result).isNotNull();
        assertThat(result.getFirst().getId())
                .isEqualTo(projects.getFirst().getId());
        verify(projectRepository).findAll();
    }

    @Test
    public void testGetAllSkillsWhenNoneFound() {
        when(projectRepository.findAll())
                .thenReturn(Collections.emptyList());

        ProjectNotFoundException exception = assertThrows(ProjectNotFoundException.class, () -> {
           projectService.getAllProjects();
        });

        assertThat(exception.getMessage())
                .isEqualTo("No projects found in database");
        verify(projectRepository).findAll();
    }

    @Test
    public void testGetAllSkillsWhenThrowsException() {
        when(projectRepository.findAll())
                .thenThrow(new RuntimeException("Error while retrieving all projects"));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            projectService.getAllProjects();
        });

        assertThat(exception.getMessage())
                .isEqualTo("Error while retrieving all projects");
        verify(projectRepository).findAll();
    }

    @Test
    public void testGetProjectByIdWhenSuccessful() {
        when(projectRepository.findById(existingProject.getId()))
                .thenReturn(Optional.of(existingProject));

        Project result = projectService.getProjectById(existingProject.getId());

        assertThat(result).isNotNull();
        assertThat(result.getId())
                .isEqualTo(existingProject.getId());
        verify(projectRepository).findById(existingProject.getId());
    }

    @Test
    public void testGetProjectByIdWhenNoneFound() {
        when(projectRepository.findById(1L))
                .thenReturn(Optional.empty());

        ProjectNotFoundException exception = assertThrows(ProjectNotFoundException.class, () -> {
           projectService.getProjectById(1L);
        });

        assertThat(exception.getMessage())
                .contains("Project not found with ID: 1");
        verify(projectRepository).findById(1L);
    }

    @Test
    public void testGetProjectByIdWhenThrowsException() {
        when(projectRepository.findById(1L))
                .thenThrow(new RuntimeException(""));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            projectService.getProjectById(1L);
        });

        assertThat(exception.getMessage())
                .contains("Error while retrieving project");
        verify(projectRepository).findById(1L);
    }

    @Test
    public void testGetFeaturedProjectsWhenSuccessful() {
        List<Project> projects = Collections.singletonList(existingProject);
        when(projectRepository.findFeatured())
                .thenReturn(projects);

        List<Project> result = projectService.getFeaturedProjects();

        assertThat(result).isNotNull();
        assertThat(result.getFirst().getId())
                .isEqualTo(projects.getFirst().getId());
        verify(projectRepository).findFeatured();
    }

    @Test
    public void testGetFeaturedProjectsWhenNoneFound() {
        when(projectRepository.findFeatured())
                .thenReturn(Collections.emptyList());

        ProjectNotFoundException exception = assertThrows(ProjectNotFoundException.class, () -> {
            projectService.getFeaturedProjects();
        });

        assertThat(exception.getMessage())
                .contains("No Featured Projects Found");
        verify(projectRepository).findFeatured();
    }

    @Test
    public void testGetFeaturedProjectsWhenThrowsException() {
        when(projectRepository.findFeatured())
                .thenThrow(new RuntimeException(""));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
           projectService.getFeaturedProjects();
        });

        assertThat(exception.getMessage())
                .contains("");
        verify(projectRepository).findFeatured();
    }

    @Test
    public void testUpdateProjectWhenSuccess() {
        Project updatedProject = new Project();
        updatedProject.setTitle("New Title");
        when(projectRepository.findById(existingProject.getId()))
                .thenReturn(Optional.of(existingProject));
        when(projectRepository.save(existingProject))
                .thenReturn(existingProject);

        Project result = projectService.updateProject(existingProject.getId(), updatedProject);

        assertThat(result.getTitle())
                .isEqualTo(updatedProject.getTitle());
        assertThat(result.getId())
                .isEqualTo(existingProject.getId());
        verify(projectRepository).save(existingProject);
    }

    @Test
    public void testUpdateProjectWhenProjectNotFound() {
        when(projectRepository.findById(existingProject.getId()))
                .thenReturn(Optional.empty());

        ProjectNotFoundException exception = assertThrows(ProjectNotFoundException.class, () -> {
           projectService.updateProject(existingProject.getId(), existingProject);
        });

        assertThat(exception.getMessage())
                .contains("Project not found with ID: ");
        verify(projectRepository).findById(existingProject.getId());
    }

    @Test
    public void testUpdateProjectWhenThrowsException() {
        when(projectRepository.findById(existingProject.getId()))
                .thenThrow(new RuntimeException(""));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            projectService.updateProject(existingProject.getId(), existingProject);
        });

        assertThat(exception.getMessage())
                .contains("Error updating project");
        verify(projectRepository).findById(existingProject.getId());
    }

    @Test
    public void testUpdateProjectWhenAllAllFieldsUpdatedSuccessfully() {
        Project updateProject = new Project();
        updateProject.setTitle("New Title");
        updateProject.setDescription("New Description");
        updateProject.setRepoLink("http://new.repo");
        updateProject.setDemoLink("http://new.demo");
        updateProject.setPhotoFile("newPhoto.jpg");
        when(projectRepository.findById(existingProject.getId()))
                .thenReturn(Optional.of(existingProject));
        when(projectRepository.save(any(Project.class)))
                .thenReturn(existingProject);

        Project result = projectService.updateProject(existingProject.getId(), updateProject);

        assertThat(result.getTitle())
                .isEqualTo(updateProject.getTitle());
        assertThat(result.getDescription())
                .isEqualTo(updateProject.getDescription());
        assertThat(result.getRepoLink())
                .isEqualTo(updateProject.getRepoLink());
        assertThat(result.getDemoLink())
                .isEqualTo(updateProject.getDemoLink());
        assertThat(result.getPhotoFile())
                .isEqualTo(updateProject.getPhotoFile());
        verify(projectRepository).save(existingProject);
    }

    @Test
    public void testSetFeaturedWhenSuccessful() {
        Boolean prevFeatured = existingProject.getIsFeatured();
        when(projectRepository.findById(existingProject.getId()))
                .thenReturn(Optional.of(existingProject));
        when(projectRepository.save(existingProject))
                .thenReturn(existingProject);

        Project result = projectService
                .setFeatured(existingProject.getId(), !existingProject.getIsFeatured());

        assertThat(result).isNotNull();
        assertThat(result.getIsFeatured())
                .isEqualTo(!prevFeatured);
        verify(projectRepository).save(existingProject);
    }

    @Test
    public void testSetFeaturedWhenProjectNotFound() {
        when(projectRepository.findById(existingProject.getId()))
                .thenReturn(Optional.empty());

        ProjectNotFoundException exception = assertThrows(ProjectNotFoundException.class, () -> {
            projectService.setFeatured(existingProject.getId(), existingProject.getIsFeatured());
        });

        assertThat(exception.getMessage())
                .contains("Project not found with ID:");
        verify(projectRepository).findById(existingProject.getId());
    }

    @Test
    public void testSetFeaturedWhenThrowsException() {
        when(projectRepository.findById(existingProject.getId()))
                .thenThrow(new RuntimeException(""));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            projectService.setFeatured(existingProject.getId(), existingProject.getIsFeatured());
        });

        assertThat(exception.getMessage())
                .contains("Error updating featured status");
        verify(projectRepository).findById(existingProject.getId());
    }

    @Test
    public void testDeleteProjectWhenSuccessful() {
        when(projectRepository.findById(existingProject.getId()))
                .thenReturn(Optional.of(existingProject));

        projectService.deleteProject(existingProject.getId());

        verify(projectRepository).deleteById(existingProject.getId());
    }
}
