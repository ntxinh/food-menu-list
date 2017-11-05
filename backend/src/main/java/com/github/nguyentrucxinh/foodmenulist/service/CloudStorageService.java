package com.github.nguyentrucxinh.foodmenulist.service;

import org.springframework.web.multipart.MultipartFile;

public interface CloudStorageService {

    String getImageUrl(MultipartFile multipartFile, String[] activeProfiles);
}
