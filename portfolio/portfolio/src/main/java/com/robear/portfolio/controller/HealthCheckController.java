package com.robear.portfolio.controller;

import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Locale;

@RestController
@RequestMapping("/api/health")
public class HealthCheckController {

    private static final Logger logger = LoggerFactory.getLogger(HealthCheckController.class);

    private final MessageSource messageSource;

    // Inject MessageSource to access internationalized messages
    public HealthCheckController(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @GetMapping
    public ResponseEntity<String> healthCheck(@RequestParam(value = "lang", defaultValue = "en") String lang) {
        try {
            // Add DB Connection Check when set up
            logger.info("Health Check: App Healthy");
            logger.info(lang);

            Locale locale = getValidLocale(lang);

            String successMessage = messageSource.getMessage("health.status.healthy", null, locale);
            return ResponseEntity.ok(successMessage);
        } catch (Exception e) {
            logger.info("Health Check: App Unhealthy");

            String errorMessage = messageSource.getMessage("health.status.unhealthy", new Object[]{e.getMessage()}, Locale.ENGLISH);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }

    private Locale getValidLocale(String lang) {
        return switch (lang.toLowerCase()) {
            case "fr" -> Locale.FRENCH;
            case "jp" -> Locale.JAPANESE;
            default -> Locale.ENGLISH;
        };
    }
}
