package com.robear.portfolio.controller.interfaces;

import org.springframework.http.ResponseEntity;

public interface IEmailController {
    ResponseEntity<Void> SendEmails();
}
