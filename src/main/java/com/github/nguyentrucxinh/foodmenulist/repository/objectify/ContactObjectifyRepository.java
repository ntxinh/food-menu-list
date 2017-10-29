package com.github.nguyentrucxinh.foodmenulist.repository.objectify;

import com.github.nguyentrucxinh.foodmenulist.domain.Contact;
import com.github.nguyentrucxinh.foodmenulist.repository.ContactRepository;
import org.springframework.stereotype.Repository;

@Repository
public class ContactObjectifyRepository extends BaseObjectifyRepository<Contact> implements ContactRepository {
}
