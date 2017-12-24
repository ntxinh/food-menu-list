package com.github.nguyentrucxinh.foodmenulist.service.impl;

import com.github.nguyentrucxinh.foodmenulist.common.constants.GoogleCloudStorageConstants;
import com.github.nguyentrucxinh.foodmenulist.common.constants.MailType;
import com.github.nguyentrucxinh.foodmenulist.common.constants.RecipientType;
import com.github.nguyentrucxinh.foodmenulist.dao.ItemDao;
import com.github.nguyentrucxinh.foodmenulist.domain.Item;
import com.github.nguyentrucxinh.foodmenulist.dto.*;
import com.github.nguyentrucxinh.foodmenulist.service.AppEngineMailApiService;
import com.github.nguyentrucxinh.foodmenulist.service.GoogleCloudStorageService;
import com.github.nguyentrucxinh.foodmenulist.service.ItemService;
import com.google.cloud.storage.Blob;
import com.google.common.collect.ImmutableMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class ItemServiceImpl implements ItemService {

    private static final Logger LOGGER = Logger.getLogger(ItemServiceImpl.class.getName());

    @Autowired
    private ItemDao itemDao;

    @Autowired
    private GoogleCloudStorageService googleCloudStorageService;

    @Autowired
    private AppEngineMailApiService appEngineMailApiService;

    @Override
    public List<Item> findAll() {
        return itemDao.findAll();
    }

    @Override
    public Item findById(Long id) {
        return itemDao.findById(id);
    }

    @Override
    public Item save(Item t) {
        return itemDao.save(t);
    }

    @Override
    public void deleteById(Long id) {
        itemDao.deleteById(id);
    }

    @Override
    public Item save(Long id, MultipartFile multipartFile, String name, String description) {
        Blob blob = googleCloudStorageService.uploadAndGetMediaLink(multipartFile, GoogleCloudStorageConstants.BUCKET_DIRECTORY_IMAGE);

        // Simple
        appEngineMailApiService.sendMail(MailType.SIMPLE, MailDto.builder()
                .recipientDto(RecipientDto.builder().recipientType(RecipientType.TO).address("nguyentrucxjnh@gmail.com").personal("Mr. Xinh").build())
                .subject("Subject 1")
                .content("Content 1")
                .build()
        );

        // MultipartFile
        try {
            appEngineMailApiService.sendMail(MailType.MULTIPART, MailDto.builder()
                    .recipientDto(RecipientDto.builder().recipientType(RecipientType.TO).address("nguyentrucxjnh@gmail.com").personal("Mr. Xinh").build())
                    .subject("Subject 2")
                    .content("<h1>Content 2<h1>")
                    .contentType("text/html")
                    .fileAttachmentDto(FileAttachmentDto.builder().fileName(multipartFile.getOriginalFilename()).content(multipartFile.getBytes()).contentType(multipartFile.getContentType()).build())
                    .build()
            );
        } catch (IOException e) {
            LOGGER.info("Throw IOException: ");
            LOGGER.log(Level.SEVERE, e.toString(), e);
        }

        // MultipartFile with template
        try {
            appEngineMailApiService.sendMail(MailType.MULTIPART, MailDto.builder()
                    .recipientDto(RecipientDto.builder().recipientType(RecipientType.TO).address("nguyentrucxjnh@gmail.com").personal("Mr. Xinh").build())
                    .subject("Subject 2")
                    .contentType("text/html")
                    .fileAttachmentDto(FileAttachmentDto.builder().fileName(multipartFile.getOriginalFilename()).content(multipartFile.getBytes()).contentType(multipartFile.getContentType()).build())
                    .useTemplate(true)
                    .templateDto(TemplateDto.builder().templateName("confirm-mail-sign-up.ftl").dataModel(ImmutableMap.of(
                            "message", "Hello world!"
                    )).build())
                    .build()
            );
        } catch (IOException e) {
            LOGGER.info("Throw IOException: ");
            LOGGER.log(Level.SEVERE, e.toString(), e);
        }

        // Blob
        appEngineMailApiService.sendMail(MailType.MULTIPART, MailDto.builder()
                .recipientDto(RecipientDto.builder().recipientType(RecipientType.TO).address("nguyentrucxjnh@gmail.com").personal("Mr. Xinh").build())
                .subject("Subject 2")
                .content("<h1>Content 2<h1>")
                .contentType("text/html")
                .fileAttachmentDto(FileAttachmentDto.builder().fileName(blob.getName()).content(blob.getContent(Blob.BlobSourceOption.generationMatch())).contentType(blob.getContentType()).build())
                .build()
        );

        Item item;

        if (id <= 0)
            item = new Item();
        else
            item = itemDao.findById(id);

        item.setName(name);
        item.setDescription(description);
        item.setImageUrl(blob.getMediaLink());
        item.setCreatedDate(new Date());
        item.setBlobId(blob.getBlobId());

        LOGGER.info(item.toString() + " saving...");

        return itemDao.save(item);
    }
}
