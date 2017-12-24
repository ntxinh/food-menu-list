package com.github.nguyentrucxinh.foodmenulist.service;

import com.github.nguyentrucxinh.foodmenulist.domain.User;

import java.util.Map;

public interface UserService extends GenericService<User> {

    Map<String, String> signUp(User user, String role);

    Map<String, String> confirmMailSignUp(String token);
}
