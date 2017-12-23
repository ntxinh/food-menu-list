package com.github.nguyentrucxinh.foodmenulist.dto;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class SenderDto {

    private String address;
    private String personal;
    private String charset;
}
