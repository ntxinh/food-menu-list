package com.github.nguyentrucxinh.foodmenulist.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/user/hello")
    public String user() {
        return "Hello User";
    }

    @GetMapping("/admin/hello")
    public String admin() {
        return "Hello Admin";
    }
}
