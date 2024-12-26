package com.robear.portfolio.controller.interfaces;

import com.robear.portfolio.model.Email;
import org.springframework.http.ResponseEntity;

public interface IEmailController {
    ResponseEntity<Void> sendEmails();
    ResponseEntity<Void> sendContact(Email email);
}
