package com.robear.portfolio.controller;

import com.robear.portfolio.controller.interfaces.ICertificationController;
import com.robear.portfolio.model.Certification;
import com.robear.portfolio.service.CertificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/certification")
public class CertificationController implements ICertificationController {
    private static final Logger logger = LoggerFactory.getLogger(CertificationController.class);

    @Autowired
    private CertificationService certificationService;

    @PostMapping
    @Override
    public ResponseEntity<Certification> createCertification(
            @RequestBody Certification certification) {
        try {
            logger.info("Creating a new certification {}", certification);
            Certification result = certificationService.addCertification(certification);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error Creating a new certification, {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    @Override
    public ResponseEntity<List<Certification>> getAllCertifications(
            @RequestParam(value = "lang", required = false, defaultValue = "en") String lang) {
        try {
            logger.info("Getting All Certifications for language: {}", lang);
            List<Certification> result = certificationService.getAllCertification(lang);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error Retrieving Certifications: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<Void> deleteCertification(
            @PathVariable Long id) {
        try {
            logger.info("Deleting certification: {}", id);
            certificationService.deleteCertification(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            logger.error("Error deleting certification: {}", id);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
