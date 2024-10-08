package com.robear.portfolio.exception;

public class EducationNotFoundException extends RuntimeException {
    public EducationNotFoundException(Long id) {
        super("Education not found with ID: " + id);
    }

    public EducationNotFoundException(String message) {
        super(message);
    }
}
