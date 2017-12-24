package com.github.nguyentrucxinh.foodmenulist.service;

import com.github.nguyentrucxinh.foodmenulist.domain.User;

public interface UserService extends GenericService<User> {

    void signUp(User user);
}
