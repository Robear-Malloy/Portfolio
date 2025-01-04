package com.robear.portfolio.repository;

import com.robear.portfolio.model.Certification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CertificationRepository extends JpaRepository<Certification, Long> { }