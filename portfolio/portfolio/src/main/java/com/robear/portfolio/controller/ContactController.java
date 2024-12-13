package com.robear.portfolio.controller;

import com.robear.portfolio.controller.interfaces.IContactController;
import com.robear.portfolio.exception.ContactNotFoundException;
import com.robear.portfolio.model.Contact;
import com.robear.portfolio.service.ContactService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contact")
public class ContactController implements IContactController {
    private static final Logger logger = LoggerFactory.getLogger(ContactController.class);

    @Autowired
    private ContactService contactService;

    @PostMapping
    @Override
    public ResponseEntity<Contact> createContact(
            @RequestBody Contact contact) {
        try {
            logger.info("Creating a new contact: {}", contact);
            Contact result = contactService.addContact(contact);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Error Creating a new Contact with info: {}. Error {}", contact, e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<List<Contact>> getAllContacts() {
        try {
            logger.info("Retrieving all contacts");
            List<Contact> result = contactService.getContacts();
            return new ResponseEntity<>(result, HttpStatus.FOUND);
        } catch (Exception e) {
            logger.error("Error retrieving all contacts. Error: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @GetMapping("/pending")
    public ResponseEntity<List<Contact>> getPendingContacts() {
        try {
            logger.info("Retrieving contacts that have not been reached out to yet.");
            List<Contact> result = contactService.getPendingContacts();
            return new ResponseEntity<>(result, HttpStatus.FOUND);
        } catch (Exception e) {
            logger.error("Error retrieving pending contacts. Error: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<Contact> updateContactStatus(
            @PathVariable("id") Long id) {
        try {
            logger.info("Updating reach out status on contact id: {}", id);
            Contact result = contactService.toggleReachedOut(id);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (ContactNotFoundException e) {
            logger.warn("No contacts found with the id: {}", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logger.error("Error updating contact status for contact id: {}. Error: {}", id, e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
