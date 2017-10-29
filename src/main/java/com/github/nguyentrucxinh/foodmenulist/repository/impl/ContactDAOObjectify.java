package com.github.nguyentrucxinh.foodmenulist.repository.impl;

import com.github.nguyentrucxinh.foodmenulist.domain.Contact;
import com.github.nguyentrucxinh.foodmenulist.repository.ContactDAO;
import com.googlecode.objectify.ObjectifyService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("objectifyDAO")
public class ContactDAOObjectify implements ContactDAO {
    ObjectifyService os;

    public void insertContact(Contact contact) {
        ObjectifyService.ofy().save().entity(contact).now();

    }

    public List getContacts() {
        List<Contact> contacts = ObjectifyService.ofy()
                .load()
                .type(Contact.class) // We want only Contact

                .list();
        System.out.println("objectify number of contacts is "+contacts.size());
        return contacts;
    }

    public Contact getContact(Long contactId) {
        Contact c = ObjectifyService.ofy().load().type(Contact.class).id(contactId).now();
        return c;
    }

    public void updateContact(Contact contact) {
        ObjectifyService.ofy().save().entity(contact).now();

    }

    public void deleteContact(Long contactId) {
        Contact c = ObjectifyService.ofy().load().type(Contact.class).id(contactId).now();
        ObjectifyService.ofy().delete().entity(c).now();
    }
}
