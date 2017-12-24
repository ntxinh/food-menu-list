package com.github.nguyentrucxinh.foodmenulist.dao.impl;

import com.github.nguyentrucxinh.foodmenulist.dao.UserDao;
import com.github.nguyentrucxinh.foodmenulist.domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.googlecode.objectify.ObjectifyService.ofy;

@Repository
public class UserDaoImpl extends GenericDaoImpl<User> implements UserDao {

    @Override
    public User findByUsername(String username) {
//        User user = ofy().load().type(User.class).filter("username", username).first().now();
        List<User> users = ofy().load().type(User.class).list();
        return users.stream().filter(o -> o.getUsername().equals(username)).findAny().orElse(null);
    }
}
