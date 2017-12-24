package com.github.nguyentrucxinh.foodmenulist.service;

import com.github.nguyentrucxinh.foodmenulist.dto.UploadResultDto;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import org.springframework.web.multipart.MultipartFile;

public interface GoogleCloudStorageService {

    UploadResultDto uploadAndGetMediaLink(MultipartFile multipartFile, String directoryPath);

    Blob readFile(BlobId blobId);

    void createBlob();

    void updateBlob();
}
