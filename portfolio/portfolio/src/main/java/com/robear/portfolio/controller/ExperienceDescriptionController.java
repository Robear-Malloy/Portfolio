package com.robear.portfolio.controller;

import com.robear.portfolio.controller.interfaces.IExperienceDescriptionController;
import com.robear.portfolio.exception.ExperienceDescriptionNotFoundException;
import com.robear.portfolio.model.ExperienceDescription;
import com.robear.portfolio.service.ExperienceDescriptionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/experience-description")
public class ExperienceDescriptionController implements IExperienceDescriptionController {

    private static final Logger logger = LoggerFactory.getLogger(ExperienceDescriptionController.class);

    @Autowired
    private ExperienceDescriptionService experienceDescriptionService;

    @Override
    @PostMapping
    public ResponseEntity<ExperienceDescription> createExperienceDescription(
            @RequestBody ExperienceDescription description) {
        try {
            logger.info("Creating a new Experience Description: {}", description);
            ExperienceDescription result = experienceDescriptionService.addExperienceDescription(description);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Error creating experience description: {}. Error: {}", description, e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @GetMapping("/{experienceId}")
    public ResponseEntity<List<ExperienceDescription>> getAllExperienceDescriptionById(
            @PathVariable Long experienceId) {
        try {
            logger.info("Getting All Experience By Description By Id: {}", experienceId);
            List<ExperienceDescription> result = experienceDescriptionService.getExperienceDescriptions(experienceId);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (ExperienceDescriptionNotFoundException e) {
            logger.warn("Could not find experience descriptions for id: {}", experienceId);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logger.error("Error retrieving experience descriptions for id: {}", experienceId);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @PutMapping
    public ResponseEntity<ExperienceDescription> updateExperienceDescription(
            @RequestBody ExperienceDescription description) {
        try {
            logger.info("Updating Experience Description: {}", description);
            ExperienceDescription result = experienceDescriptionService.updateExperienceDescription(description);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (ExperienceDescriptionNotFoundException e) {
            logger.warn("Could not find Experience Descriptions for: {}", description);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logger.error("Error Updating Experience Description: {}, Error: {}", description, e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @DeleteMapping
    public ResponseEntity<Void> deleteExperienceDescription(ExperienceDescription description) {
        try {
            logger.info("Deleting Experience Description: {}", description);
            experienceDescriptionService.deleteExperienceDescription(description);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ExperienceDescriptionNotFoundException e) {
            logger.warn("Unable to find a matching description to delete");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logger.error("Error Deleting Experience Description: {}. Error: {}", description, e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
