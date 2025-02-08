package com.robear.portfolio.service;

import com.robear.portfolio.model.Contact;
import com.robear.portfolio.repository.ContactRepository;
import com.robear.portfolio.service.interfaces.IContactService;
import com.robear.portfolio.exception.ContactNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ContactService implements IContactService {
    private final static Logger logger = LoggerFactory.getLogger(ContactService.class);
    private final ContactRepository contactRepository;

    @Autowired
    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository; 
    }

    @Override
    public Contact addContact(Contact contact) {
        try {
            contact.setDateSent(LocalDateTime.now());
            contact.setReachedOut(false);
            logger.info("Adding Contact: {}", contact);
            return contactRepository.save(contact);
        } catch (Exception e) {
            logger.error("Error adding contact to the database");
            throw new RuntimeException("Error Adding Contact to Database");
        }
    }

    @Override
    public List<Contact> getContacts() {
        try {
            logger.info("Retrieving All Contacts");
            return contactRepository.findAll();
        } catch (Exception e) {
            logger.error("Error retrieving all contacts from database.");
            throw new RuntimeException("Error Retrieving Contacts from Database");
        }
    }

    @Override
    public List<Contact> getPendingContacts() {
        try {
            logger.info("Retrieving all pending contacts.");
            List<Contact> contacts = contactRepository.findPendingContacts();
            if (contacts.isEmpty()) {
                throw new ContactNotFoundException("Pending Contacts not Found");
            }
            return contacts;
        } catch (ContactNotFoundException e) {
            logger.warn("No pending contacts");
            throw new ContactNotFoundException("");
        } catch (Exception e) {
            logger.error("Error attempting to get pending contacts");
            throw new RuntimeException("Error Retrieving Pending Contacts to Database");
        }
    }

    @Override
    public Contact toggleReachedOut(Long id) {
        try {
            Contact contact = contactRepository.findById(id)
                    .orElseThrow(() ->
                        new ContactNotFoundException(id));
            logger.info("Toggling contact id: {} reached out to: {}", id, !contact.getReachedOut());
            contact.setReachedOut(!contact.getReachedOut());
            return contactRepository.save(contact);
        } catch (ContactNotFoundException e) {
            logger.warn("No contact was found to toggle pending flag.");
            throw new ContactNotFoundException(id);
        } catch (Exception e) {
            logger.error("Error Toggling Pending Flag");
            throw new RuntimeException("Error Adding Contact to Database");
        }
    }
}
