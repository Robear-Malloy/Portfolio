package com.robear.portfolio.service;

import com.robear.portfolio.model.Contact;
import com.robear.portfolio.model.Email;
import com.robear.portfolio.repository.ContactRepository;
import com.robear.portfolio.service.interfaces.IContactService;
import com.robear.portfolio.exception.ContactNotFoundException;
import com.robear.portfolio.util.EmailHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService implements IContactService {
    private static Logger logger = LoggerFactory.getLogger(ContactService.class);
    private final ContactRepository contactRepository;
    private final EmailHelper emailHelper;

    @Autowired
    public ContactService(ContactRepository contactRepository, EmailHelper emailHelper) {
        this.contactRepository = contactRepository;
        this.emailHelper = emailHelper; }

    @Override
    public Contact addContact(Contact contact) {
        try {
            logger.info("Adding Contact: {} to Database", contact);
            //sendEmail(contact);
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
            logger.info("Toggling contact id: {} to true", id);
            Contact contact = contactRepository.findById(id)
                    .orElseThrow(() -> {
                        return new ContactNotFoundException(id);
                    });
            contact.setReachedOut(true);
            return contactRepository.save(contact);
        } catch (ContactNotFoundException e) {
            logger.warn("No contact was found to toggle pending flag.");
            throw new ContactNotFoundException(id);
        } catch (Exception e) {
            logger.error("Error Toggling Pending Flag");
            throw new RuntimeException("Error Adding Contact to Database");
        }
    }

    private void sendEmail(Contact contact) {
        StringBuilder body = new StringBuilder();
        body.append("<html>")
                .append("<body>")
                .append("<h1>Pending Contacts</h1>")
                .append("<p><b>Name:</b> ").append(contact.getName()).append("</p>")
                .append("<p><b>Email:</b> ").append(contact.getEmail()).append("</p>")
                .append("<p><b>Company:</b> ").append(contact.getCompany()).append("</p>")
                .append("<p><b>Description:</b> ").append(contact.getDescription()).append("</p>")
                .append("</body>")
                .append("</html>");

        Email email = new Email();
        email.setSubject("[Important]: New Contact Reached Out");
        email.setBody(body.toString());
        emailHelper.sendEmail(email);
    }
}
