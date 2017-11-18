package com.github.nguyentrucxinh.foodmenulist.dao;

import com.github.nguyentrucxinh.foodmenulist.domain.ApplicationUser;

public interface ApplicationUserDao extends GenericDao<ApplicationUser> {

    ApplicationUser findByUsername(String username);
}
