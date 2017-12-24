package com.github.nguyentrucxinh.foodmenulist.service;

import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import org.springframework.web.multipart.MultipartFile;

public interface GoogleCloudStorageService {

    Blob uploadAndGetMediaLink(MultipartFile multipartFile, String directoryPath);

    Blob readFile(BlobId blobId);

    Blob readFile(String bucket, String name, Long generation);

    void createBlob();

    void updateBlob();
}
