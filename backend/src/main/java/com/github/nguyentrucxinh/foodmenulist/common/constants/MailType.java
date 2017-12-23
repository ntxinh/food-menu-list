package com.github.nguyentrucxinh.foodmenulist.common.constants;

import java.io.Serializable;

public class MailType implements Serializable {

    public static final MailType SIMPLE = new MailType("Simple");
    public static final MailType MULTIPART = new MailType("Multipart");

    private String type;

    private MailType(String type) {
        this.type = type;
    }

    public String toString() {
        return this.type;
    }
}
