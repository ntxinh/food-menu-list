package com.github.nguyentrucxinh.foodmenulist.config;

import com.github.nguyentrucxinh.foodmenulist.common.constants.SecurityConstants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebConfig {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping(SecurityConstants.LOG_IN_URL).exposedHeaders(SecurityConstants.HEADER_STRING);
                registry.addMapping(SecurityConstants.API_ADMIN_URL + "/**");
                registry.addMapping(SecurityConstants.API_USER_URL + "/**");
            }
        };
    }
}
