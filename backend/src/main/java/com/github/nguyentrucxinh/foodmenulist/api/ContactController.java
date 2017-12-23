package com.github.nguyentrucxinh.foodmenulist.api;

import com.github.nguyentrucxinh.foodmenulist.common.constants.SecurityConstants;
import com.github.nguyentrucxinh.foodmenulist.domain.Contact;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(SecurityConstants.API_ADMIN_URL + "/contacts")
public class ContactController extends GenericControllerImpl<Contact> {

}
