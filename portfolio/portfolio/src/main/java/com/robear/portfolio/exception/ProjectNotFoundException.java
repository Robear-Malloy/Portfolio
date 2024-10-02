package com.robear.portfolio.exception;

public class ProjectNotFoundException extends RuntimeException {
  public ProjectNotFoundException(Long id) {
    super("Project not found with ID: " + id);
  }

  public ProjectNotFoundException(Boolean isFeatured) { super("No Featured Projects Found"); }

  public ProjectNotFoundException(String message) {
    super(message);
  }
}
