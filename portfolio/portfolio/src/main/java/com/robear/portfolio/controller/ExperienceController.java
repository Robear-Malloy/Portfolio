package com.robear.portfolio.controller;

import com.robear.portfolio.interfaces.IExperienceController;
import com.robear.portfolio.exception.ExperienceNotFoundException;
import com.robear.portfolio.model.Experience;
import com.robear.portfolio.service.ExperienceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/experiences")
public class ExperienceController implements IExperienceController {

    private static final Logger logger = LoggerFactory.getLogger(ExperienceController.class);

    @Autowired
    private ExperienceService experienceService;

    @PostMapping
    @Override
    public ResponseEntity<Experience> createExperience(
            @RequestBody Experience experience) {

    }

    @GetMapping
    @Override
    public ResponesEntity<List<Experience>> getAllExperiences() {

    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<Experience> getExperienceById(
            @PathVariable("id") Long id) {

    }

    @PostMapping("/{id}")
    @Override
    public ResponseEntity<Experience> updateExperienceById(
            @PathVariable("id") Long id, @RequestBody Experience experience) {

    }

    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<Void> deleteExperienceById(
            @PathVariable("id") Long id) {

    }
}