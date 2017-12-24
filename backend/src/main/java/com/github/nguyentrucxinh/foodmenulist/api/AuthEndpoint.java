package com.github.nguyentrucxinh.foodmenulist.api;

import com.github.nguyentrucxinh.foodmenulist.common.constants.SecurityConstants;
import com.github.nguyentrucxinh.foodmenulist.domain.User;
import com.github.nguyentrucxinh.foodmenulist.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class AuthEndpoint {

    @Autowired
    private UserService userService;

    @PostMapping(SecurityConstants.SIGN_UP_URL)
    public Map<String, String> signUp(@RequestBody User user) {

        return userService.signUp(user, "ROLE_USER");
    }

    @PostMapping(SecurityConstants.SIGN_UP_ADMIN_URL)
    public Map<String, String> signUpAdmin(@RequestBody User user) {

        return userService.signUp(user, "ROLE_ADMIN");
    }

    @GetMapping(SecurityConstants.CONFIRM_MAIL_SIGN_UP_URL)
    public Map<String, String> confirmMailSignUp(@RequestParam String token) {

        return userService.confirmMailSignUp(token);
    }
}
