package com.github.nguyentrucxinh.foodmenulist.config;

import com.github.nguyentrucxinh.foodmenulist.domain.*;
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
        ObjectifyService.register(User.class);
        ObjectifyService.register(Authority.class);
        ObjectifyService.register(Task.class);
    }

    public void contextDestroyed(ServletContextEvent event) {
        // App Engine does not currently invoke this method.

    }
}

