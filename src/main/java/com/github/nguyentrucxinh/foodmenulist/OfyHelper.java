package com.github.nguyentrucxinh.foodmenulist;

import com.googlecode.objectify.ObjectifyService;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class OfyHelper implements ServletContextListener {
    public void contextInitialized(ServletContextEvent event) {
        // This will be invoked as part of a warmup request, or the first user request if no warmup

        // request.

        ObjectifyService.register(Contact.class);
    }

    public void contextDestroyed(ServletContextEvent event) {
        // App Engine does not currently invoke this method.

    }
}
