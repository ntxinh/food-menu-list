package com.github.nguyentrucxinh.foodmenulist.api;

import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.logging.Logger;

@RestControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger log = Logger.getLogger(RestResponseEntityExceptionHandler.class.getName());

}
