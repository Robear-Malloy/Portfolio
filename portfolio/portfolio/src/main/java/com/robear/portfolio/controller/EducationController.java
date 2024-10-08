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

import java.util.Collections;
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
        Education res = educationService.addEducation(education);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping
    @Override
    public ResponseEntity<List<Education>> getAllEducation() {
        List<Education> educations = Collections.EMPTY_LIST;
        return new ResponseEntity<>(educations , HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<Education> getEducationById(
            @PathVariable("id") Long id) {
        return new ResponseEntity<>(new Education(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @Override
    public ResponseEntity<Education> updateEducationById(
            @PathVariable("id") Long id, @RequestBody Education education) {
        return new ResponseEntity<>(new Education(), HttpStatus.OK);
    }

    @PatchMapping("/gpa/{id}")
    @Override
    public ResponseEntity<Education> updateGpaById(
            @PathVariable("id") Long id, @RequestBody Float gpa) {
        return new ResponseEntity<>(new Education(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<Void> deleteEducation(
            @PathVariable Long id) {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
