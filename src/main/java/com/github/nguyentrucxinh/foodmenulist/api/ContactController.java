package com.github.nguyentrucxinh.foodmenulist.api;

import com.github.nguyentrucxinh.foodmenulist.dao.ContactDao;
import com.github.nguyentrucxinh.foodmenulist.domain.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contact")
public class ContactController {

    @Autowired
    private ContactDao contactDao;

    @GetMapping
    public List<Contact> findAll() {
        return contactDao.findAll();
    }

}
