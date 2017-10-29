package com.github.nguyentrucxinh.foodmenulist.api;

import com.github.nguyentrucxinh.foodmenulist.domain.Contact;
import com.github.nguyentrucxinh.foodmenulist.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contact")
public class ContactController {

    @Autowired
    private ContactRepository contactRepository;

    @GetMapping
    public List findAll() {
        return contactRepository.findAll();
    }

    @GetMapping("/{id}")
    public Contact findById(@PathVariable Long id) {
        return contactRepository.findById(id);
    }

    @PostMapping()
    public Long create(@RequestBody Contact contact) {
        return contactRepository.create(contact).getId();
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody Contact contact) {
        contactRepository.update(id, contact);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        contactRepository.delete(id);
    }
}
