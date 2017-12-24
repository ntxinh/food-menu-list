package com.github.nguyentrucxinh.foodmenulist.service.impl;

import com.github.nguyentrucxinh.foodmenulist.dao.AuthorityDao;
import com.github.nguyentrucxinh.foodmenulist.dao.UserDao;
import com.github.nguyentrucxinh.foodmenulist.domain.Authority;
import com.github.nguyentrucxinh.foodmenulist.domain.User;
import com.github.nguyentrucxinh.foodmenulist.service.UserService;
import com.googlecode.objectify.Ref;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private AuthorityDao authorityDao;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public User findById(Long id) {
        return null;
    }

    @Override
    public User save(User t) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void signUp(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setEnable(false);
        User userSaved = userDao.save(user);

        Authority authority = new Authority();
        authority.setAuthority("ROLE_ADMIN");
        authority.setUser(Ref.create(userSaved));
        authorityDao.save(authority);

        // Send mail
    }
}
