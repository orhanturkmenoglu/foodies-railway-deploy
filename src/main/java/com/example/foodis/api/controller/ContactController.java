package com.example.foodis.api.controller;

import com.example.foodis.api.io.ContactRequest;
import com.example.foodis.api.io.ContactResponse;
import com.example.foodis.api.service.ContactService;
import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contact")
@AllArgsConstructor
public class ContactController {
    private ContactService contactService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ContactResponse addContact(@RequestBody  ContactRequest contactRequest) throws MessagingException {
        return contactService.addContact(contactRequest);
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<ContactResponse> getContactsAll() {
        return contactService.getContactsAll();
    }

    @PatchMapping("/{id}/read")
    @ResponseStatus(HttpStatus.OK)
    public void markAsRead(@PathVariable String id) {
        contactService.markAsRead(id);
    }

    @DeleteMapping("/{contactId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteContactById(@PathVariable String contactId) {
        contactService.deleteContactById(contactId);
    }

}
