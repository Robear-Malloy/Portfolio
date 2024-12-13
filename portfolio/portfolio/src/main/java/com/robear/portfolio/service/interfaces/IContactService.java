package com.robear.portfolio.service.interfaces;

import com.robear.portfolio.model.Contact;
import java.util.List;

public interface IContactService {
    Contact addContact(Contact contact);
    List<Contact> getContacts();
    List<Contact> getPendingContacts();
    Contact toggleReachedOut(Long id);
}
