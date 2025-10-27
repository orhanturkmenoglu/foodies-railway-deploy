package com.example.foodis.api.service;

import com.example.foodis.api.entity.ContactEntity;
import com.example.foodis.api.io.ContactRequest;
import com.example.foodis.api.io.ContactResponse;
import com.example.foodis.api.repository.ContactRepository;
import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
@AllArgsConstructor
public class ContactServiceImpl implements ContactService {

    private final ContactRepository contactRepository;
    private final EmailService emailService;

    @Override
    public ContactResponse addContact(ContactRequest contactRequest) throws MessagingException {
        ContactEntity contactEntity = convertToEntity(contactRequest);
        ContactEntity savedEntity = contactRepository.save(contactEntity);
      //  emailService.sendHtmlMail(contactRequest);
        return  convertToResponse(savedEntity);
    }

    @Override
    public List<ContactResponse> getContactsAll() {
        return contactRepository.findAll()
                .stream()
                .map(this::convertToResponse)
                .toList();
    }

    @Override
    public ContactResponse getContactById(String contactId) {
        ContactEntity contactEntity = contactRepository.findById(contactId)
                .orElseThrow(() -> new RuntimeException("Contact not found with id: " + contactId));

        return convertToResponse(contactEntity);
    }

    @Override
    public void markAsRead(String contactId) {
        ContactEntity contact = contactRepository.findById(contactId)
                .orElseThrow(() -> new RuntimeException("Contact not found with id: " + contactId));

        contact.setStatus("Read");
        contactRepository.save(contact);
    }

    @Override
    public void deleteContactById(String contactId) {
        ContactEntity existingContact  = contactRepository.findById(contactId)
                .orElseThrow(() -> new RuntimeException("Contact not found with id: " + contactId));

        contactRepository.deleteById(existingContact.getId());
    }

    private ContactEntity convertToEntity(ContactRequest contactRequest) {
        return ContactEntity.builder()
                .firstName(contactRequest.getFirstName())
                .lastName(contactRequest.getLastName())
                .email(contactRequest.getEmail())
                .message(contactRequest.getMessage())
                .status("New")
                .date(LocalDateTime.now())
                .build();
    }

    private ContactResponse convertToResponse(ContactEntity savedEntity) {
        return ContactResponse.builder()
                .id(savedEntity.getId())
                .firstName(savedEntity.getFirstName())
                .lastName(savedEntity.getLastName())
                .email(savedEntity.getEmail())
                .message(savedEntity.getMessage())
                .status(savedEntity.getStatus())
                .date(savedEntity.getDate())
                .build();
    }
}
