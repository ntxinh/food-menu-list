package com.github.nguyentrucxinh.foodmenulist.service;

import com.github.nguyentrucxinh.foodmenulist.common.constants.MailType;
import com.github.nguyentrucxinh.foodmenulist.dto.MailDto;
import com.google.cloud.storage.Blob;
import org.springframework.web.multipart.MultipartFile;

public interface AppEngineMailApiService {

    void sendSimpleMail();

    void sendMultipartMail(byte[] content, String fileName, String contentType);

    void sendMultipartMail(MultipartFile multipartFile);

    void sendMultipartMail(Blob blob);

    void sendMail(MailType mailType, MailDto mailDto);
}
