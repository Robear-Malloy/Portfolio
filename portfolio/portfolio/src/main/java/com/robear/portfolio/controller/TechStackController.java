package com.robear.portfolio.controller;

import com.robear.portfolio.controller.interfaces.ITechStackController;
import com.robear.portfolio.enums.TechType;
import com.robear.portfolio.exception.InvalidTechStackException;
import com.robear.portfolio.exception.TechStackNotFoundException;
import com.robear.portfolio.model.TechStack;
import com.robear.portfolio.service.TechStackService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tech")
public class TechStackController implements ITechStackController {

    private final static Logger logger = LoggerFactory.getLogger(TechStackController.class);

    @Autowired
    private TechStackService techStackService;

    @PostMapping
    @Override
    public ResponseEntity<TechStack> addTechStack(
            @RequestBody TechStack techStack) {
        try {
            logger.info("Creating Tech Stack: {}",
                    techStack);
            TechStack result = techStackService.createTechStack(techStack);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } catch (InvalidTechStackException e) {
            logger.warn("Invalid tech stack entered");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            logger.error("Error creating a new tech stack: {}, Exception: {}",
                    techStack, e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/project/{id}")
    @Override
    public ResponseEntity<List<TechStack>> getProjectTechStack(
            @PathVariable Long id) {
        try {
            logger.info("Retrieving tech stack for project id: {}", id);
            List<TechStack> result = techStackService.getTechStack(id, TechType.PROJECT);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (TechStackNotFoundException e) {
            logger.warn("Unable to find tech stack for project id: {}. Error: {}",
                    id, e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logger.error("Error getting tech stack for project id: {}. Error: {}",
                    id, e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/experience/{id}")
    @Override
    public ResponseEntity<List<TechStack>> getExperienceTechStack(
            @PathVariable Long id) {
        try {
            logger.info("Retrieving tech stack for experience id: {}", id);
            List<TechStack> result = techStackService.getTechStack(id, TechType.EXPERIENCE);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (TechStackNotFoundException e) {
            logger.warn("Unable to find tech stack for experience id: {}. Error: {}",
                    id, e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logger.error("Error getting tech stack for experience id: {}. Error: {}",
                    id, e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<Void> deleteTechStack(
            @PathVariable Long id) {
        try {
            logger.info("Deleting tech stack id: {}", id);
            techStackService.deleteTechStack(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (TechStackNotFoundException e) {
            logger.warn("Unable to find tech stack for tech stack id: {}. Error: {}",
                    id, e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logger.error("Error getting tech stack for tech stack id: {}. Error: {}",
                    id, e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
