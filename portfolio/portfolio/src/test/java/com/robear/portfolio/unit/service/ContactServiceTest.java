package com.robear.portfolio.unit.service;

import com.robear.portfolio.exception.ContactNotFoundException;
import com.robear.portfolio.model.Contact;
import com.robear.portfolio.repository.ContactRepository;
import com.robear.portfolio.service.ContactService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class ContactServiceTest {

    @InjectMocks
    private ContactService contactService;

    @Mock
    private ContactRepository contactRepository;

    private final Contact contact = new Contact();

    @BeforeEach
    public void setup() {
        contact.setId(1L);
        contact.setName("John Doe");
        contact.setEmail("john.doe@example.com");
        contact.setReachedOut(0);
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddContactWhenSuccessful() {
        when(contactRepository.save(contact)).thenReturn(contact);

        Contact result = contactService.addContact(contact);

        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(contact);
        verify(contactRepository).save(contact);
    }

    @Test
    public void testAddContactWhenThrowsException() {
        when(contactRepository.save(contact)).thenThrow(new RuntimeException("Error Adding Contact to Database"));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            contactService.addContact(contact);
        });

        assertThat(exception.getMessage()).isEqualTo("Error Adding Contact to Database");
        verify(contactRepository).save(contact);
    }

    @Test
    public void testGetAllContactsWhenRecordsFound() {
        List<Contact> contactList = Collections.singletonList(contact);
        when(contactRepository.findAll()).thenReturn(contactList);

        List<Contact> result = contactService.getContacts();

        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(contactList);
        verify(contactRepository).findAll();
    }

    @Test
    public void testGetPendingContactsWhenRecordsFound() {
        List<Contact> contactList = Collections.singletonList(contact);
        when(contactRepository.findPendingContacts()).thenReturn(contactList);

        List<Contact> result = contactService.getPendingContacts();

        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(contactList);
        verify(contactRepository).findPendingContacts();
    }

    @Test
    public void testGetPendingContactsWhenNoRecordsFound() {
        when(contactRepository.findPendingContacts()).thenReturn(Collections.emptyList());

        ContactNotFoundException exception = assertThrows(ContactNotFoundException.class, () -> {
            contactService.getPendingContacts();
        });

        assertThat(exception.getMessage()).isEqualTo("");
        verify(contactRepository).findPendingContacts();
    }

    @Test
    public void testToggleReachedOutWhenSuccessful() {
        when(contactRepository.findById(contact.getId())).thenReturn(Optional.of(contact));
        when(contactRepository.save(contact)).thenReturn(contact);

        Contact result = contactService.toggleReachedOut(contact.getId());

        assertThat(result).isNotNull();
        assertThat(result.getReachedOut()).isEqualTo(1);
        verify(contactRepository).save(contact);
    }

    @Test
    public void testToggleReachedOutWhenContactNotFound() {
        when(contactRepository.findById(contact.getId())).thenReturn(Optional.empty());

        ContactNotFoundException exception = assertThrows(ContactNotFoundException.class, () -> {
            contactService.toggleReachedOut(contact.getId());
        });

        assertThat(exception.getMessage()).isEqualTo("Contact not found with ID: " + contact.getId());
        verify(contactRepository).findById(contact.getId());
    }

    @Test
    public void testToggleReachedOutWhenThrowsException() {
        when(contactRepository.findById(contact.getId()))
                .thenThrow(new RuntimeException("Error Adding Contact to Database"));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            contactService.toggleReachedOut(contact.getId());
        });

        assertThat(exception.getMessage()).isEqualTo("Error Adding Contact to Database");
        verify(contactRepository).findById(contact.getId());
    }
}
