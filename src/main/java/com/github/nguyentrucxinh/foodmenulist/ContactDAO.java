package com.github.nguyentrucxinh.foodmenulist;

import java.util.List;

public interface ContactDAO {

    void insertContact(Contact contact);
    List getContacts();
    Contact getContact(Long contactId);
    void updateContact(Contact contact);
    void deleteContact(Long contactId);
}
