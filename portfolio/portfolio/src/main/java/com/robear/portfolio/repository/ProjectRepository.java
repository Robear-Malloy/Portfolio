package com.robear.portfolio.repository;

import com.robear.portfolio.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    @Query("SELECT p FROM Project p WHERE p.isFeatured = true AND p.language = :language")
    List<Project> findFeatured(String language);

    @Query("SELECT p FROM Project p WHERE language = :language")
    List<Project> findAllByLanguage(String language);
}
