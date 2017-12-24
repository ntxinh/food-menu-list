package com.github.nguyentrucxinh.foodmenulist.dto;

import com.google.cloud.storage.BlobId;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class UploadResultDto {
    private String mediaLink;
    private BlobId blobId;
}
