package com.github.nguyentrucxinh.foodmenulist.config;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The SimpleCORSFilter class is a standard web Filter which intercepts all inbound HTTP requests. The filter sets
 * several Headers on the HTTP response which inform a browser that the web services handle Cross-Origin requests.
 *
 * @author Xinh Nguyen
 */
@Component
public class SimpleCorsFilter extends GenericFilterBean {

    @Override
    public void doFilter(final ServletRequest req, final ServletResponse resp, final FilterChain chain)
            throws IOException, ServletException {

        final HttpServletResponse response = (HttpServletResponse) resp;
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "DELETE, GET, OPTIONS, PATCH, POST, PUT");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with, content-type");

        chain.doFilter(req, resp);
    }

}
