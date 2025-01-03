package com.robear.portfolio.exception;

public class VideoNotFoundException extends RuntimeException {
  public VideoNotFoundException(Long id) {
    super("Video not found with ID: " + id);
  }
  public VideoNotFoundException(String message) {
    super(message);
  }
}
