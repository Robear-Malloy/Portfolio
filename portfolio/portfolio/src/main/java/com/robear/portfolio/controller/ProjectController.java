package com.robear.portfolio.controller;

import com.robear.portfolio.controller.interfaces.IProjectController;
import com.robear.portfolio.exception.ProjectNotFoundException;
import com.robear.portfolio.model.Project;
import com.robear.portfolio.service.ProjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController implements IProjectController {

    private static final Logger logger = LoggerFactory.getLogger(ProjectController.class);

    @Autowired
    private ProjectService projectService;

    @PostMapping
    @Override
    public ResponseEntity<Project> createProject(
            @RequestBody Project project) {
        try {
            logger.info("Adding Project: {}", project);
            Project newProject = projectService.addProject(project);
            return new ResponseEntity<>(newProject, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Error Creating Project: {}, Exception: {}",
                    project, e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    @Override
    public ResponseEntity<List<Project>> getAllProjects(
            @RequestParam(value = "lang", required = false, defaultValue = "en") String lang
    ) {
        try {
            logger.info("Getting all Projects");
            List<Project> projects = projectService.getAllProjects(lang);
            return new ResponseEntity<>(projects, HttpStatus.OK);
        } catch (ProjectNotFoundException e) {
            logger.warn("No Projects Found. Exception: {}",
                    e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logger.error("Error returning all projects. Exception: {}",
                    e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/id/{id}")
    @Override
    public ResponseEntity<Project> getProjectById(
            @PathVariable("id") Long id) {
        try {
            logger.info("Retrieving information for Project ID: {}", id);
            Project project = projectService.getProjectById(id);
            return new ResponseEntity<>(project, HttpStatus.OK);
        } catch (ProjectNotFoundException e) {
            logger.warn("Attempted to retrieve Project ID: {}, but not found. {}",
                    id, e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logger.error("Error retrieving Project ID: {}. Exception: {}",
                    id, e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/featured")
    @Override
    public ResponseEntity<List<Project>> getFeaturedProjects(
            @RequestParam(value = "lang", required = false, defaultValue = "en") String lang
    ) {
        try {
            logger.info("Retrieving Featured Projects");
            List<Project> projects = projectService.getFeaturedProjects(lang);
            return new ResponseEntity<>(projects, HttpStatus.OK);
        } catch (ProjectNotFoundException e) {
            logger.warn("Attempted to Find Featured Projects, but None Found. Exception: {}",
                    e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logger.error("Error Retrieving Featured Projects. Exception {}",
                    e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/id/{id}")
    @Override
    public ResponseEntity<Void> deleteProjectById(
            @PathVariable("id") Long id) {
        try {
            logger.info("Deleting Project ID: {}", id);
            projectService.deleteProject(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (ProjectNotFoundException e) {
            logger.warn("Attempted to Delete, but no Project ID: {} was found. Exception: {}",
                    id, e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logger.error("Encounted Error Deleting Project ID: {}. Exception: {}",
                    id, e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/id/{id}")
    @Override
    public ResponseEntity<Project> updateProjectById(
            @PathVariable("id") Long id, @RequestBody Project project) {
        try {
            logger.info("Updating Project Info: {} For Project ID: {}", project, id);
            Project newProject = projectService.updateProject(id, project);
            return new ResponseEntity<>(newProject, HttpStatus.OK);
        } catch (ProjectNotFoundException e) {
            logger.warn("Attempted to Update Project: {}, but no Project ID: {} was found. Exception: {}",
                    project, id, e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logger.error("Error Updating Project ID: {}. Exception {}",
                    id, e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/featured/{id}")
    @Override
    public ResponseEntity<Project> setProjectIsFeatured(
            @PathVariable("id") Long id, @RequestParam Boolean isFeatured) {
        try {
            logger.info("");
            Project newProject = projectService.setFeatured(id, isFeatured);
            return new ResponseEntity<>(newProject, HttpStatus.OK);
        } catch (ProjectNotFoundException e) {
            logger.warn("Attempted to Update Featured Flag on Project ID: {}, but not found. Exception: {}",
                    id, e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logger.error("Error Updating Featured Flag Project ID: {}. Exception {}",
                    id, e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
