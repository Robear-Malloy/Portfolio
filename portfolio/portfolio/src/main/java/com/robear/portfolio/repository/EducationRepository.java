package com.robear.portfolio.repository;

import com.robear.portfolio.model.Education;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EducationRepository extends JpaRepository<Education, Long> {
}
