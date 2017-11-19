package com.github.nguyentrucxinh.foodmenulist.api;

import com.github.nguyentrucxinh.foodmenulist.api.exceptions.TokenExpiredException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.logging.Logger;

@RestControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger log = Logger.getLogger(RestResponseEntityExceptionHandler.class.getName());

    @ExceptionHandler(TokenExpiredException.class)
    public ResponseEntity<Object> handleTokenExpiredException(final TokenExpiredException ex, final WebRequest request) {
        log.info("> handleException");
        log.info("- Exception: " + ex);
        log.info("< handleException");
        return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }
}
