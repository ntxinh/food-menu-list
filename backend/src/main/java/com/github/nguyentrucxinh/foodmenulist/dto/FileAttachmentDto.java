package com.github.nguyentrucxinh.foodmenulist.dto;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class FileAttachmentDto {

    private String fileName;
    private byte[] content;
    private String contentType;
}
