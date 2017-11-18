//package com.github.nguyentrucxinh.foodmenulist.config;
//
//import com.googlecode.objectify.ObjectifyFilter;
//import org.springframework.boot.web.servlet.FilterRegistrationBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class FilterConfig {
//
//    @Bean
//    public FilterRegistrationBean someFilterRegistration() {
//
//        FilterRegistrationBean registration = new FilterRegistrationBean();
//        registration.setFilter(objectifyFilter());
//        registration.addUrlPatterns("/*");
//        registration.setOrder(1);
//        return registration;
//    }
//
//    @Bean
//    public ObjectifyFilter objectifyFilter() {
//        return new ObjectifyFilter();
//    }
//}
