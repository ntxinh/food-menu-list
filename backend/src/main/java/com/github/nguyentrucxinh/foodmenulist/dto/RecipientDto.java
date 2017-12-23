package com.github.nguyentrucxinh.foodmenulist.dto;

import com.github.nguyentrucxinh.foodmenulist.common.constants.RecipientType;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class RecipientDto {

    private String address;
    private String personal;
    private String charset;
    private RecipientType recipientType;
}
