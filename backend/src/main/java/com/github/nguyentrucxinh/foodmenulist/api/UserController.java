package com.github.nguyentrucxinh.foodmenulist.api;

import com.github.nguyentrucxinh.foodmenulist.common.constants.SecurityConstants;
import com.github.nguyentrucxinh.foodmenulist.domain.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(SecurityConstants.API_ADMIN_URL + "/users")
public class UserController extends GenericControllerImpl<User> {
}
