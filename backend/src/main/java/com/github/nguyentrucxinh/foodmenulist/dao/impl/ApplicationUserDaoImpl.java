package com.github.nguyentrucxinh.foodmenulist.dao.impl;

import com.github.nguyentrucxinh.foodmenulist.dao.ApplicationUserDao;
import com.github.nguyentrucxinh.foodmenulist.domain.ApplicationUser;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.googlecode.objectify.ObjectifyService.ofy;

@Repository
public class ApplicationUserDaoImpl extends GenericDaoImpl<ApplicationUser> implements ApplicationUserDao {

    @Override
    public ApplicationUser findByUsername(String username) {
        //        ApplicationUser applicationUser = ofy().load().type(ApplicationUser.class).filter("username", username).first().now();
        List<ApplicationUser> applicationUsers = ofy().load().type(ApplicationUser.class).list();
        ApplicationUser applicationUser = applicationUsers.stream().filter(o -> o.getUsername().equals(username)).findFirst().get();

        return applicationUser;
    }
}
