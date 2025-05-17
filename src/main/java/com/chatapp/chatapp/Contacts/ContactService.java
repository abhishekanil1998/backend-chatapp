package com.chatapp.chatapp.Contacts;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {

    @Autowired
    private Contactrepo contactrepo;

    public List<Contactmodel> getContactsByUserId(Integer userId) {
        return contactrepo.findByUserId(userId);
    }

    public Contactmodel addContact(Contactmodel contact) {
        contact.setName(contact.getName());
        contact.setId(contact.getId());
        contact.setUserId(contact.getUserId());
        contact.setPhoneNumber(contact.getPhoneNumber());
        return contactrepo.save(contact);
    }

    public void deleteContact(Long contactId) {
        contactrepo.deleteById(contactId);
    }
}
