package com.robear.portfolio.repository;

import com.robear.portfolio.model.Education;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EducationRepository extends JpaRepository<Education, Long> {
    @Query("SELECT e FROM Education e WHERE language = :language")
    List<Education> findAllByLanguage(String language);
}
