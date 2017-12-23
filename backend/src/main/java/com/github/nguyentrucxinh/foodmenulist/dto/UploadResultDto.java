package com.github.nguyentrucxinh.foodmenulist.dto;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class UploadResultDto {
    private String mediaLink;
    private String blobName;
    private Long generation;
}
