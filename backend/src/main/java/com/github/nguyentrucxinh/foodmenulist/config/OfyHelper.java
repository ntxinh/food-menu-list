package com.github.nguyentrucxinh.foodmenulist.config;

import com.github.nguyentrucxinh.foodmenulist.domain.ApplicationUser;
import com.github.nguyentrucxinh.foodmenulist.domain.Contact;
import com.github.nguyentrucxinh.foodmenulist.domain.Item;
import com.github.nguyentrucxinh.foodmenulist.domain.Task;
import com.googlecode.objectify.ObjectifyService;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class OfyHelper implements ServletContextListener {
    public void contextInitialized(ServletContextEvent event) {
        // This will be invoked as part of a warmup request, or the first user request if no warmup

        // request.

        ObjectifyService.register(Contact.class);
        ObjectifyService.register(Item.class);
        ObjectifyService.register(ApplicationUser.class);
        ObjectifyService.register(Task.class);
    }

    public void contextDestroyed(ServletContextEvent event) {
        // App Engine does not currently invoke this method.

    }
}

