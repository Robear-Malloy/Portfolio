package com.robear.portfolio.repository;

import com.robear.portfolio.model.Video;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoRepository extends JpaRepository<Video, Long> { }