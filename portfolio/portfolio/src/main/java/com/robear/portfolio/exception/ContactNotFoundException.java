package com.robear.portfolio.exception;

public class ContactNotFoundException extends RuntimeException {
  public ContactNotFoundException(Long id) {
    super("Contact not found with ID: " + id);
  }

  public ContactNotFoundException(String message) {
    super(message);
  }
}
