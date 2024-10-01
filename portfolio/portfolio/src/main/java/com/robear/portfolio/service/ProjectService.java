package com.robear.portfolio.service;

import com.robear.portfolio.model.Project;
import com.robear.portfolio.service.interfaces.IProjectService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService implements IProjectService {
    @Override
    public Project addProject(Project project) {
        return null;
    }

    @Override
    public List<Project> getAllProjects() {
        return List.of();
    }

    @Override
    public Project getProjectById(Long id) {
        return null;
    }

    @Override
    public List<Project> getFeaturedProjects() {
        return List.of();
    }

    @Override
    public Project updateProject(Long id, Project project) {
        return null;
    }

    @Override
    public Project setFeatured(Long id, Boolean isFeatured) {
        return null;
    }

    @Override
    public void deleteProject(Long id) {

    }
}
