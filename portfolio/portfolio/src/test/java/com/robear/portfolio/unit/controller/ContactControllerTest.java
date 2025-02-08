package com.robear.portfolio.unit.controller;

import com.robear.portfolio.controller.ContactController;
import com.robear.portfolio.exception.ContactNotFoundException;
import com.robear.portfolio.model.Contact;
import com.robear.portfolio.service.ContactService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class ContactControllerTest {

    private final Contact contact = new Contact();

    @InjectMocks
    private ContactController contactController;

    @Mock
    private ContactService contactService;

    @BeforeEach
    public void setup() {
        contact.setId(1L);
        contact.setName("John Doe");
        contact.setEmail("john.doe@example.com");
        contact.setCompany("Example Co.");
        contact.setReachedOut(false);
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateContactWhenSuccessful() {
        when(contactService.addContact(contact)).thenReturn(contact);

        ResponseEntity<Contact> response = contactController.createContact(contact);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody()).isEqualTo(contact);
    }

    @Test
    public void testCreateContactWhenThrowsException() {
        when(contactService.addContact(contact)).thenThrow(new RuntimeException("Error"));

        ResponseEntity<Contact> response = contactController.createContact(contact);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(response.getBody()).isNull();
    }

    @Test
    public void testGetAllContactsWhenRecordsExist() {
        List<Contact> contactList = Collections.singletonList(contact);
        when(contactService.getContacts()).thenReturn(contactList);

        ResponseEntity<List<Contact>> response = contactController.getAllContacts();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.FOUND);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody()).isEqualTo(contactList);
    }

    @Test
    public void testGetAllContactsWhenThrowsException() {
        when(contactService.getContacts()).thenThrow(new RuntimeException("Error"));

        ResponseEntity<List<Contact>> response = contactController.getAllContacts();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(response.getBody()).isNull();
    }

    @Test
    public void testGetPendingContactsWhenRecordsExist() {
        List<Contact> contactList = Collections.singletonList(contact);
        when(contactService.getPendingContacts()).thenReturn(contactList);

        ResponseEntity<List<Contact>> response = contactController.getPendingContacts();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.FOUND);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody()).isEqualTo(contactList);
    }

    @Test
    public void testGetPendingContactsWhenThrowsException() {
        when(contactService.getPendingContacts()).thenThrow(new RuntimeException("Error"));

        ResponseEntity<List<Contact>> response = contactController.getPendingContacts();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(response.getBody()).isNull();
    }

    @Test
    public void testUpdateContactStatusWhenSuccessful() {
        when(contactService.toggleReachedOut(contact.getId())).thenReturn(contact);

        ResponseEntity<Contact> response = contactController.updateContactStatus(contact.getId());

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody()).isEqualTo(contact);
    }

    @Test
    public void testUpdateContactStatusWhenRecordNotFound() {
        when(contactService.toggleReachedOut(contact.getId())).thenThrow(new ContactNotFoundException("Contact not found"));

        ResponseEntity<Contact> response = contactController.updateContactStatus(contact.getId());

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody()).isNull();
    }

    @Test
    public void testUpdateContactStatusWhenThrowsException() {
        when(contactService.toggleReachedOut(contact.getId())).thenThrow(new RuntimeException("Error"));

        ResponseEntity<Contact> response = contactController.updateContactStatus(contact.getId());

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(response.getBody()).isNull();
    }
}
