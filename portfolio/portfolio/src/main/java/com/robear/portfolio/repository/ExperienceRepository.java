package com.robear.portfolio.repository;

import com.robear.portfolio.model.Experience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ExperienceRepository extends JpaRepository<Experience, Long> {
    @Query("SELECT e FROM Experience e WHERE e.isFeatured = true AND language = :language")
    List<Experience> findFeatured(String language);

    @Query("SELECT e FROM Experience e WHERE language = :language")
    List<Experience> findAllByLanguage(String language);
}

