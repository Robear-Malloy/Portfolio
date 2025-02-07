package com.robear.portfolio.controller;

import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
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
    private final JdbcTemplate jdbcTemplate;

    public HealthCheckController(MessageSource messageSource, JdbcTemplate jdbcTemplate) {
        this.messageSource = messageSource;
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping
    public ResponseEntity<String> healthCheck(@RequestParam(value = "lang", defaultValue = "en") String lang) {
        try {
            checkDatabaseConnection();
            Locale locale = getValidLocale(lang);
            String successMessage = messageSource.getMessage("health.status.healthy", null, locale);
            logger.info("Health Check: App Healthy");
            return ResponseEntity.ok(successMessage);
        } catch (Exception e) {
            logger.error("Health Check: App Unhealthy - " + e.getMessage(), e);
            String errorMessage = messageSource.getMessage("health.status.unhealthy", new Object[]{e.getMessage()}, Locale.ENGLISH);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }

    private void checkDatabaseConnection() throws Exception {
        try {
            jdbcTemplate.execute("SELECT 1");
            logger.info("Health Check: Database is healthy.");
        } catch (Exception e) {
            logger.error("Health Check: Database connection failed.");
            throw new Exception("Database connection failed: " + e.getMessage());
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
