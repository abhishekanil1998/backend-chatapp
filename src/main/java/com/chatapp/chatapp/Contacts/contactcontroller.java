package com.chatapp.chatapp.Contacts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping(path="api/chatapp")
@RestController
public class contactcontroller {


        @Autowired
        private ContactService contactService;

        @GetMapping("contact/{userId}")
        public List<Contactmodel> getContacts(@PathVariable Integer userId) {
            return contactService.getContactsByUserId(userId);
        }

        @PostMapping("/addcontact")
        public Contactmodel addContact(@RequestBody Contactmodel contact) {
            return contactService.addContact(contact);
        }

        @DeleteMapping("contact/{contactId}")
        public void deleteContact(@PathVariable Long contactId) {
            contactService.deleteContact(contactId);
        }
    }


