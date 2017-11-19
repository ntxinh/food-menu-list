package com.github.nguyentrucxinh.foodmenulist.api;

import com.github.nguyentrucxinh.foodmenulist.dao.AuthorityDao;
import com.github.nguyentrucxinh.foodmenulist.dao.UserDao;
import com.github.nguyentrucxinh.foodmenulist.domain.Authority;
import com.github.nguyentrucxinh.foodmenulist.domain.User;
import com.googlecode.objectify.Ref;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private AuthorityDao authorityDao;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/sign-up")
    public User signUp(@RequestBody User user) {

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setEnable(true);
        User userSaved = userDao.save(user);

        Authority authority = new Authority();
        authority.setAuthority("ROLE_ADMIN");
        authority.setUser(Ref.create(userSaved));
        authorityDao.save(authority);

        return userSaved;
    }
}
