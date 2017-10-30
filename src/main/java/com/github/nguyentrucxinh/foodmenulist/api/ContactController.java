package com.github.nguyentrucxinh.foodmenulist.api;

import com.github.nguyentrucxinh.foodmenulist.api.impl.GenericControllerImpl;
import com.github.nguyentrucxinh.foodmenulist.domain.Contact;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contact")
public class ContactController extends GenericControllerImpl<Contact> {

}
