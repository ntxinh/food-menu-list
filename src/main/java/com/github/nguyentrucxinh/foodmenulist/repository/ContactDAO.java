package com.github.nguyentrucxinh.foodmenulist.repository;

import com.github.nguyentrucxinh.foodmenulist.domain.Contact;

import java.util.List;

public interface ContactDAO {

    void insertContact(Contact contact);
    List getContacts();
    Contact getContact(Long contactId);
    void updateContact(Contact contact);
    void deleteContact(Long contactId);
}
