package com.github.nguyentrucxinh.foodmenulist.service;

import org.springframework.web.multipart.MultipartFile;

public interface CloudStorageService {

    String uploadAndGetMediaLink(MultipartFile multipartFile, String directoryPath);
}
