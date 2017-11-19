package com.github.nguyentrucxinh.foodmenulist.dao;

import com.github.nguyentrucxinh.foodmenulist.domain.User;

public interface UserDao extends GenericDao<User> {

    User findByUsername(String username);
}
