package com.example.foodis.api.service;

import com.example.foodis.api.io.ContactRequest;
import com.example.foodis.api.io.ContactResponse;
import jakarta.mail.MessagingException;

import java.util.List;

public interface ContactService {
    ContactResponse addContact(ContactRequest contactRequest) throws MessagingException;

    List<ContactResponse> getContactsAll();

    ContactResponse getContactById(String contactId);

    void markAsRead(String contactId);

    void deleteContactById(String contactId);
}
