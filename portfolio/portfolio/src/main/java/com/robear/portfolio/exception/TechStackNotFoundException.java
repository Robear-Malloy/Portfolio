package com.robear.portfolio.exception;

public class TechStackNotFoundException extends RuntimeException {
    public TechStackNotFoundException(Long id) {
        super("Tech Stack not found with ID: " + id);
    }
    public TechStackNotFoundException(String message) {
        super(message);
    }
}
