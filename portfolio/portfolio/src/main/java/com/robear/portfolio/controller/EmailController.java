package com.robear.portfolio.controller;

import com.robear.portfolio.controller.interfaces.IEmailController;
import com.robear.portfolio.service.EmailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/email")
public class EmailController implements IEmailController {
    private static final Logger logger = LoggerFactory.getLogger(EmailController.class);

    @Autowired
    private EmailService emailService;

    @Override
    @PostMapping("/sendPending")
    public ResponseEntity<Void> SendEmails() {
        try {
            logger.info("Sending emails to all pending emails.");
            emailService.sendPendingEmails();
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error Sending All Pending Emails");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/test")
    public ResponseEntity<Void> test() {
        logger.info("Entering Test Email");
        emailService.sendTestEmail();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
