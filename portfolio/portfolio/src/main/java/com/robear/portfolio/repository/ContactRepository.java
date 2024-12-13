package com.robear.portfolio.repository;

import com.robear.portfolio.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ContactRepository extends JpaRepository<Contact, Long> {
    @Query("SELECT c FROM Contact c WHERE reachedOut = 0")
    List<Contact> findPendingContacts();
}
