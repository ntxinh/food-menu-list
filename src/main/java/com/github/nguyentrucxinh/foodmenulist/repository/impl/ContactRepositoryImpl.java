package com.github.nguyentrucxinh.foodmenulist.repository.impl;

import com.github.nguyentrucxinh.foodmenulist.domain.Contact;
import com.github.nguyentrucxinh.foodmenulist.repository.ContactRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.googlecode.objectify.ObjectifyService.ofy;

@Repository
public class ContactRepositoryImpl implements ContactRepository {

    @Override
    public void insertContact(Contact contact) {
        ofy().save().entity(contact).now();
    }

    @Override
    public List<Contact> getContacts() {
        return ofy()
                .load()
                .type(Contact.class) // We want only Contact
                .list();
    }

    @Override
    public Contact getContact(Long contactId) {
        return ofy().load().type(Contact.class).id(contactId).now();
    }

    @Override
    public void updateContact(Contact contact) {
        ofy().save().entity(contact).now();
    }

    @Override
    public void deleteContact(Long contactId) {
        Contact c = ofy().load().type(Contact.class).id(contactId).now();
        ofy().delete().entity(c).now();
    }
}
