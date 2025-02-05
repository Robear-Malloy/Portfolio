package com.robear.portfolio.repository;

import com.robear.portfolio.model.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VideoRepository extends JpaRepository<Video, Long> {
    @Query("SELECT v FROM Video v WHERE language = :language")
    List<Video> findAllByLanguage(String language);
}