package xxxxxx.yyyyyy.zzzzzz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contact")
public class ContactController {

    @Autowired
    private ContactDAO contactDAO;

    @GetMapping
    public List findAll() {
        return contactDAO.getContacts();
    }

    @GetMapping("/{id}")
    public Contact findById(@PathVariable Long id) {
        return contactDAO.getContact(id);
    }

    @PostMapping()
    public Long create(@RequestBody Contact contact) {
        contactDAO.insertContact(contact);
        return contact.getId();
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody Contact contact) {
        Contact contactUpdate = contactDAO.getContact(id);
        contactUpdate.setName(contact.getName());
        contactDAO.updateContact(contactUpdate);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        contactDAO.deleteContact(id);
    }
}
