package com.robear.portfolio.repository;

import com.robear.portfolio.model.ExperienceDescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ExperienceDescriptionRepository extends JpaRepository<ExperienceDescription, Long> {
    @Query("SELECT ed FROM ExperienceDescription ed WHERE id = :id")
    List<ExperienceDescription> findAllDescriptionByExperienceId(Long id);
}
