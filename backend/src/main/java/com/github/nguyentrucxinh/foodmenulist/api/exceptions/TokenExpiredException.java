package com.github.nguyentrucxinh.foodmenulist.api.exceptions;

public class TokenExpiredException extends RuntimeException {

    public TokenExpiredException(String message) {
        super(message);
    }
}
