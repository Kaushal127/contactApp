package com.contactApp.demo.controllers;


import com.contactApp.demo.models.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.contactApp.demo.repositories.ContactRepository;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
public class ContactController {
    @Autowired
    private ContactRepository contactRepository ;

    // to get all the cpntacts
    @RequestMapping("/getContacts")
    public List<Contact> getContacts(){
        return contactRepository.findAll();
    }

    // to create contact
    @RequestMapping(method = RequestMethod.PUT ,value = "/createContact")
    public Contact createContact(@RequestBody Contact contact){
        return contactRepository.save(contact);
    }

    // to update the contact
    @RequestMapping(method = RequestMethod.POST, value = "/updateContact/{id}")
    public Contact updateContact(@PathVariable Long id, @RequestBody Contact updatedContact) {
        Optional<Contact> optionalContact = contactRepository.findById(id);

        if (optionalContact.isPresent()) {
            Contact existingContact = optionalContact.get();
            existingContact.setName(updatedContact.getName());
            existingContact.setPhoneNumber(updatedContact.getPhoneNumber());
            existingContact.setEmail(updatedContact.getEmail());

            return contactRepository.save(existingContact);
        } else {
            throw new IllegalArgumentException("Contact not found with id: " + id);
        }
    }

    // to delete contact
    @RequestMapping(method = RequestMethod.DELETE , value = "/deleteContact/{id}")
    public void deleteContact(@PathVariable Long id){
        contactRepository.deleteById(id);
    }
    // to search by name
    @RequestMapping(method = RequestMethod.GET , value = "/getContact")
    public Contact searchContact(@RequestParam String name){
        return contactRepository.findByName(name) ;
    }
}
