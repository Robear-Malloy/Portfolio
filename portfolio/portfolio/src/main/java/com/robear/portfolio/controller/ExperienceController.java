package com.robear.portfolio.controller;

import com.robear.portfolio.controller.interfaces.IExperienceController;
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
        try {
            logger.info("Creating New Experience: {}",
                    experience);
            Experience result = experienceService.addExperience(experience);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } catch(Exception e) {
            logger.error("Error While Creating Experience: {}. Exception {}",
                    experience, e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    @Override
    public ResponseEntity<List<Experience>> getAllExperiences(
            @RequestParam(value = "lang", required = false, defaultValue = "en") String lang
    ) {
        try {
            logger.info("Retrieving All Experiences");
            List<Experience> result = experienceService.getAllExperiences(lang);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (ExperienceNotFoundException e) {
            logger.warn("No Experiences Found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logger.error("Error Retrieving All Experiences. Exception: {}",
                    e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<Experience> getExperienceById(
            @PathVariable("id") Long id) {
        try {
            logger.info("Retrieving Experience ID: {}",
                    id);
            Experience result = experienceService.getExperienceById(id);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (ExperienceNotFoundException e) {
            logger.warn("No Experiences Found with ID: {}. Exception: {}", id, e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logger.error("Error Occurred While Retrieving ID: {}. Exception: {}", id, e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/isFeatured")
    @Override
    public ResponseEntity<List<Experience>> getFeaturedExperiences(
            @RequestParam(value = "lang", required = false, defaultValue = "en") String lang
    ) {
        try {
            logger.info("Retrieving All Featured Experiences");
            List<Experience> result = experienceService.getAllFeaturedExperiences(lang);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (ExperienceNotFoundException e) {
            logger.warn("No Featured Experiences Found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logger.error("Error Retrieving All Featured Experiences. Exception: {}",
                    e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/{id}")
    @Override
    public ResponseEntity<Experience> updateExperienceById(
            @PathVariable("id") Long id, @RequestBody Experience experience) {
        try {
            logger.info("Updating ID: {} With Experience: {}", id, experience);
            Experience result = experienceService.updateExperience(id, experience);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (ExperienceNotFoundException e) {
            logger.warn("No Experience Found to Update with ID: {}. Exception: {}",
                    id, e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logger.error("Error Occurred While Updating ID: {}. Exception: {}",
                    id, e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<Void> deleteExperienceById(
            @PathVariable("id") Long id) {
        try {
            logger.info("Deleting Experience ID: {}", id);
            experienceService.deleteExperience(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (ExperienceNotFoundException e) {
            logger.warn("No Experience Found to Delete with ID: {}. Exception: {}",
                    id, e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logger.error("Error Occurred While Deleting ID: {}. Exception: {}",
                id, e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}