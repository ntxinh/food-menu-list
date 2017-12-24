package com.github.nguyentrucxinh.foodmenulist.service.impl;

import com.github.nguyentrucxinh.foodmenulist.common.constants.MailType;
import com.github.nguyentrucxinh.foodmenulist.common.constants.RecipientType;
import com.github.nguyentrucxinh.foodmenulist.common.utils.JwtsUtils;
import com.github.nguyentrucxinh.foodmenulist.dao.AuthorityDao;
import com.github.nguyentrucxinh.foodmenulist.dao.UserDao;
import com.github.nguyentrucxinh.foodmenulist.domain.Authority;
import com.github.nguyentrucxinh.foodmenulist.domain.User;
import com.github.nguyentrucxinh.foodmenulist.dto.MailDto;
import com.github.nguyentrucxinh.foodmenulist.dto.RecipientDto;
import com.github.nguyentrucxinh.foodmenulist.service.AppEngineMailApiService;
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

    @Autowired
    private AppEngineMailApiService appEngineMailApiService;

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public User findById(Long id) {
        return userDao.findById(id);
    }

    @Override
    public User save(User t) {
        return userDao.save(t);
    }

    @Override
    public void deleteById(Long id) {
        userDao.deleteById(id);
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
        String token = JwtsUtils.createToken(user.getUsername());

        String host = "http://localhost:8080";
        String path = "/users/confirm-mail-sign-up?token=";

        appEngineMailApiService.sendMail(MailType.SIMPLE, MailDto.builder()
                .recipientDto(RecipientDto.builder().recipientType(RecipientType.TO).address("nguyentrucxjnh@gmail.com").personal("Mr. Xinh").build())
                .subject("Welcome to FoodMenuList " + user.getUsername() + "!" + host + path + token)
                .content("Please click here to confirm your email address and activate your account " + host + path + token)
                .build()
        );
    }

    @Override
    public void confirmMailSignUp(String token) {
        String username = JwtsUtils.parseToken(token);
        User user = userDao.findByUsername(username);
        user.setEnable(true);
        userDao.save(user);
    }
}
