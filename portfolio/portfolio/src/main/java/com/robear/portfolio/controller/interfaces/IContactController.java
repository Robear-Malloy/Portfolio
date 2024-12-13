package com.robear.portfolio.controller.interfaces;

import com.robear.portfolio.model.Contact;
import org.springframework.http.ResponseEntity;
import java.util.List;

public interface IContactController {
    ResponseEntity<Contact> createContact(Contact contact);
    ResponseEntity<List<Contact>> getAllContacts();
    ResponseEntity<List<Contact>> getPendingContacts();
    ResponseEntity<Contact> updateContactStatus(Long id);
}
