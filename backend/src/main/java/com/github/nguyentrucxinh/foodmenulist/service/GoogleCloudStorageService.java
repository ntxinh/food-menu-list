package com.github.nguyentrucxinh.foodmenulist.service;

import org.springframework.web.multipart.MultipartFile;

public interface GoogleCloudStorageService {

    String uploadAndGetMediaLink(MultipartFile multipartFile, String directoryPath);
}
