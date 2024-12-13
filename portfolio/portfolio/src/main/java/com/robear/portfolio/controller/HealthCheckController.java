package com.robear.portfolio.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/health")
public class HealthCheckController {

    private static final Logger logger = LoggerFactory.getLogger(HealthCheckController.class);

    @GetMapping
    public ResponseEntity<String> healthCheck() {
        try {
            // Add DB Connection Check when set up
            logger.info("Health Check: App Healthy");
            return ResponseEntity.ok("Application is health");
        } catch (Exception e) {
            logger.info("Health Check: App Unhealthy");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Application is unhealthy: " + e.getMessage());
        }
    }
}
