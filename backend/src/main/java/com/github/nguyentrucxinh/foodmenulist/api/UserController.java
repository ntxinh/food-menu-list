package com.github.nguyentrucxinh.foodmenulist.api;

import com.github.nguyentrucxinh.foodmenulist.domain.User;
import com.github.nguyentrucxinh.foodmenulist.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController extends GenericControllerImpl<User> {

    @Autowired
    private UserService userService;

    @PostMapping("/sign-up")
    public Map<String, String> signUp(@RequestBody User user) {

        return userService.signUp(user);
    }

    @GetMapping("/confirm-mail-sign-up")
    public Map<String, String> signUp(@RequestParam String token) {

        return userService.confirmMailSignUp(token);
    }
}
