package com.robear.portfolio.service;

import com.robear.portfolio.exception.ProjectNotFoundException;
import com.robear.portfolio.model.Project;
import com.robear.portfolio.repository.ProjectRepository;
import com.robear.portfolio.service.interfaces.IProjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService implements IProjectService {

    private final static Logger logger = LoggerFactory.getLogger(ProjectService.class);
    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public Project addProject(Project project) {
        try {
            logger.info("Saving Project {} to Database", project);
            return projectRepository.save(project);
        } catch (Exception e) {
            logger.error("Error adding project: {}", project);
            throw new RuntimeException("Unable to add project");
        }
    }

    @Cacheable(value="projects")
    @Override
    public List<Project> getAllProjects(String lang) {
        try {
            logger.info("Getting all projects from database");
            List<Project> projects = projectRepository.findAllByLanguage(lang);

            if (projects.isEmpty()) {
                throw new ProjectNotFoundException("No projects found in database");
            }

            return projects;
        } catch (ProjectNotFoundException e) {
            logger.warn("No Projects found in Database");
            throw e;
        } catch (Exception e) {
            logger.error("Unable to retrieve all projects");
            throw new RuntimeException("Error while retrieving all projects");
        }
    }

    @Override
    public Project getProjectById(Long id) {
        try {
            logger.info("Retrieving Project DB Record for ID: {}", id);
            return projectRepository.findById(id)
                    .orElseThrow(() -> new ProjectNotFoundException(id));
        } catch (ProjectNotFoundException e) {
            logger.warn("Project not found with ID: {}", id);
            throw e;
        } catch (Exception e) {
            logger.error("Unable to retrieve Project ID: {}", id);
            throw new RuntimeException("Error while retrieving project");
        }
    }

    @Cacheable(value="projects")
    @Override
    public List<Project> getFeaturedProjects(String lang) {
        try {
            logger.info("Retrieving All Featured Projects.");
            List<Project> featuredProjects = projectRepository.findFeatured(lang);
            if (featuredProjects.isEmpty()) {
                throw new ProjectNotFoundException(true);
            }
            return featuredProjects;
        } catch (ProjectNotFoundException e) {
            logger.warn("Unable to find any featured projects");
            throw e;
        } catch (Exception e) {
            logger.error("Unable to retrieve featured projects");
            throw new RuntimeException("Error while retrieving projects");
        }
    }

    @Override
    public Project updateProject(Long id, Project project) {
        try {
            logger.info("Updating project information");

            Project existingProject = projectRepository.findById(id)
                    .orElseThrow(() -> new ProjectNotFoundException(id));

            if (project.getTitle() != null && !project.getTitle().isEmpty()) {
                existingProject.setTitle(project.getTitle());
            }
            if (project.getDescription() != null && !project.getDescription().isEmpty()) {
                existingProject.setDescription(project.getDescription());
            }
            if (project.getRepoLink() != null && !project.getRepoLink().isEmpty()) {
                existingProject.setRepoLink(project.getRepoLink());
            }
            if (project.getDemoLink() != null && !project.getDemoLink().isEmpty()) {
                existingProject.setDemoLink(project.getDemoLink());
            }
            if (project.getPhotoFile() != null && !project.getPhotoFile().isEmpty()) {
                existingProject.setPhotoFile(project.getPhotoFile());
            }

            return projectRepository.save(existingProject);
        } catch (ProjectNotFoundException e) {
            logger.warn("Project not found: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Error updating project ID: {}. Exception: {}", id, e.getMessage());
            throw new RuntimeException("Error updating project", e);
        }
    }

    @Override
    public Project setFeatured(Long id, Boolean isFeatured) {
        try {
            logger.info("Setting project ID: {} as featured: {}", id, isFeatured);

            Project existingProject = projectRepository.findById(id)
                    .orElseThrow(() -> new ProjectNotFoundException(id));

            existingProject.setIsFeatured(isFeatured);

            return projectRepository.save(existingProject);
        } catch (ProjectNotFoundException e) {
            logger.warn("Project not found in database: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Error updating featured status for project ID: {}. Exception: {}", id, e.getMessage());
            throw new RuntimeException("Error updating featured status", e);
        }
    }

    @Override
    public void deleteProject(Long id) {
        getProjectById(id);
        projectRepository.deleteById(id);
        logger.info("Deleted project ID: {}", id);
    }
}
