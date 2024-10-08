package com.robear.portfolio.controller;

import com.robear.portfolio.controller.interfaces.IEducationController;
import com.robear.portfolio.exception.EducationNotFoundException;
import com.robear.portfolio.model.Education;
import com.robear.portfolio.service.EducationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/education")
public class EducationController implements IEducationController {

    private static final Logger logger = LoggerFactory.getLogger(EducationController.class);

    @Autowired
    private EducationService educationService;

    @PostMapping
    @Override
    public ResponseEntity<Education> createEducation(
            @RequestBody Education education) {
        try {
            logger.info("Creating Education: {}",
                    education);
            Education result = educationService.addEducation(education);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Error Creating Education: {}. Exception: {}",
                    education, e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    @Override
    public ResponseEntity<List<Education>> getAllEducation() {
        try {
            logger.info("Getting All Education Records");
            List<Education> result = educationService.getAllEducation();
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (EducationNotFoundException e) {
            logger.warn("No Education Records Found. Exception: {}",
                    e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logger.error("Error occurred while retrieving education. Exception {}",
                    e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<Education> getEducationById(
            @PathVariable("id") Long id) {
        try {
            logger.info("Retrieving Education Information For ID: {}",
                    id);
            Education result = educationService.getEducationById(id);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (EducationNotFoundException e) {
            logger.warn("No Education Records Found for ID: {}. Exception: {}",
                    id, e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logger.error("Error Retrieving Education ID: {}. Exception: {}",
                    id, e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    @Override
    public ResponseEntity<Education> updateEducationById(
            @PathVariable("id") Long id, @RequestBody Education education) {
        try {
            logger.info("Updating Education ID: {}, With Data: {}",
                    id, education);
            Education result = educationService.updateEducationById(id, education);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (EducationNotFoundException e) {
            logger.warn("No Education Records Found with ID: {}. Exception: {}",
                    id, e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e){
            logger.error("Error Occurred While Updating Education ID: {}. Exception: {}",
                    id, e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/gpa/{id}")
    @Override
    public ResponseEntity<Education> updateGpaById(
            @PathVariable("id") Long id, @RequestBody Float gpa) {
        try {
            logger.info("Updating Education ID: {} to a GPA: {}",
                    id, gpa);
            Education result = educationService.updateGpaById(id, gpa);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (EducationNotFoundException e) {
            logger.warn("No Education Found to Update GPA for ID: {}. Exception: {}",
                    id, e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logger.error("Error Occurred While Updating GPA for ID: {}. Exception: {}",
                    id, e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<Void> deleteEducation(
            @PathVariable Long id) {
        try {
            logger.info("Deleting Education ID: {}",
                    id);
            educationService.deleteEducation(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (EducationNotFoundException e) {
            logger.warn("No Education Records to Delete Found with ID: {}. Exception: {}",
                    id, e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e){
            logger.error("Error Occurred While Deleting Education ID: {}. Exception: {}",
                    id, e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
