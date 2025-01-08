package com.robear.portfolio.repository;

import com.robear.portfolio.model.Certification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CertificationRepository extends JpaRepository<Certification, Long> {
    @Query("SELECT c FROM Certification c WHERE language = :language")
    List<Certification> findAllByLanguage(String language);
}