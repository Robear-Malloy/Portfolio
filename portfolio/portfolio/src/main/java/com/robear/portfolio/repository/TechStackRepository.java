package com.robear.portfolio.repository;

import com.robear.portfolio.model.TechStack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TechStackRepository extends JpaRepository<TechStack, Long> {
    @Query("SELECT ts FROM TechStack ts WHERE experienceId = :experienceId")
    List<TechStack> findExperienceTechStack(Long experienceId);

    @Query("SELECT ts FROM TechStack ts WHERE projectId = :projectId")
    List<TechStack> findProjectTechStack(Long projectId);
}
