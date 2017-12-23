package com.github.nguyentrucxinh.foodmenulist.common.constants;

import java.io.Serializable;

public class RecipientType implements Serializable {

    public static final RecipientType TO = new RecipientType("To");
    public static final RecipientType CC = new RecipientType("Cc");
    public static final RecipientType BCC = new RecipientType("Bcc");

    private String type;

    private RecipientType(String type) {
        this.type = type;
    }

    public String toString() {
        return this.type;
    }
}
