package com.robear.portfolio.exception;

public class ExperienceNotFoundException extends RuntimeException {
    public ExperienceNotFoundException(Long id) {
        super("Project not found with ID: " + id);
    }

    public ExperienceNotFoundException(String message) {
        super(message);
    }
}