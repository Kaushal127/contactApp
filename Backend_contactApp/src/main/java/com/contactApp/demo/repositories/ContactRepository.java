package com.contactApp.demo.repositories;


import com.contactApp.demo.models.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

    Contact findByName(java.lang.String name);
}
