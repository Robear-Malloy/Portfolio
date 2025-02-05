package com.robear.portfolio.repository;

import com.robear.portfolio.model.Skill;
import com.robear.portfolio.enums.SkillType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;


public interface SkillRepository extends JpaRepository<Skill, Long> {
    @Query("SELECT s FROM Skill s WHERE type = :type")
    List<Skill> findByType(SkillType type);

    @Query("SELECT s FROM Skill s WHERE language = :language")
    List<Skill> findByLanguage(String language);
}