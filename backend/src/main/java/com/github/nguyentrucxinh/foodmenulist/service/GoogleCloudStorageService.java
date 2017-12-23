package com.github.nguyentrucxinh.foodmenulist.service;

import com.github.nguyentrucxinh.foodmenulist.dto.UploadResultDto;
import org.springframework.web.multipart.MultipartFile;

public interface GoogleCloudStorageService {

    UploadResultDto uploadAndGetMediaLink(MultipartFile multipartFile, String directoryPath);

    byte[] readFile(String blobName, Long generation);

    void createBlob();

    void updateBlob();
}
