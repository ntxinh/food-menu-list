package com.github.nguyentrucxinh.foodmenulist.service;

import org.springframework.web.multipart.MultipartFile;

public interface AppEngineMailApiService {

    void sendSimpleMail();

    void sendMultipartMail(MultipartFile multipartFile);
}
