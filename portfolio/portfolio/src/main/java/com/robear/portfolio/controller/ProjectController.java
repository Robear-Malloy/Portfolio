package com.robear.portfolio.controller;

import com.robear.portfolio.controller.interfaces.IProjectController;
import com.robear.portfolio.model.Project;
import com.robear.portfolio.service.ProjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController implements IProjectController {

    private static final Logger logger = LoggerFactory.getLogger(ProjectController.class);

    @Autowired
    private ProjectService projectService;

    @Override
    public ResponseEntity<Project> createProject(Project project) {
        return null;
    }

    @Override
    public ResponseEntity<List<Project>> getAllProjects() {
        return null;
    }

    @Override
    public ResponseEntity<Project> getProjectById(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<List<Project>> getFeaturedProjects() {
        return null;
    }

    @Override
    public ResponseEntity<Void> deleteProjectById(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<Project> updateProjectById(Long id, Project project) {
        return null;
    }

    @Override
    public ResponseEntity<Project> setProjectIsFeatured(Long id, Boolean isFeatured) {
        return null;
    }
}
