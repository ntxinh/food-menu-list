package com.github.nguyentrucxinh.foodmenulist.api;

import com.github.nguyentrucxinh.foodmenulist.domain.Contact;
import com.github.nguyentrucxinh.foodmenulist.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contact")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @GetMapping
    public List findAll() {
        return contactService.findAll();
    }

    @GetMapping("/{id}")
    public Contact findById(@PathVariable Long id) {
        return contactService.findById(id);
    }

    @PostMapping()
    public Long create(@RequestBody Contact contact) {
        return contactService.save(contact).getId();
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody Contact contact) {
        contactService.save(contact);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        contactService.delete(id);
    }
}
