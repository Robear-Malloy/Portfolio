package com.robear.portfolio.controller.interfaces;

import com.robear.portfolio.model.Project;
import org.springframework.http.ResponseEntity;
import java.util.List;

public interface IProjectController {
    ResponseEntity<Project> createProject(Project project);
    ResponseEntity<List<Project>> getAllProjects(String lang);
    ResponseEntity<Project> getProjectById(Long id);
    ResponseEntity<List<Project>> getFeaturedProjects(String lang);
    ResponseEntity<Void> deleteProjectById(Long id);
    ResponseEntity<Project> updateProjectById(Long id, Project project);
    ResponseEntity<Project> setProjectIsFeatured(Long id, Boolean isFeatured);
}
