package com.github.nguyentrucxinh.foodmenulist.service;

import org.springframework.web.multipart.MultipartFile;

public interface AppEngineMailApiService {

    void sendSimpleMail();

    void sendMultipartMail(byte[] content, String fileName, String contentType);

    void sendMultipartMail(MultipartFile multipartFile);

    void sendMultipartMail(String blobName, Long generation);
}
