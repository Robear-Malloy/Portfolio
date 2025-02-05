package com.robear.portfolio.service.interfaces;

import com.robear.portfolio.model.Project;

import java.util.List;

public interface IProjectService {
    Project addProject(Project project);
    List<Project> getAllProjects(String lang);
    Project getProjectById(Long id);
    List<Project> getFeaturedProjects(String lang);
    Project updateProject(Long id, Project project);
    Project setFeatured(Long id, Boolean isFeatured);
    void deleteProject(Long id);
}
