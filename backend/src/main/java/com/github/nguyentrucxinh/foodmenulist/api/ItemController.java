package com.github.nguyentrucxinh.foodmenulist.api;

import com.github.nguyentrucxinh.foodmenulist.common.constants.GoogleCloudStorageConstants;
import com.github.nguyentrucxinh.foodmenulist.common.constants.MailType;
import com.github.nguyentrucxinh.foodmenulist.common.constants.RecipientType;
import com.github.nguyentrucxinh.foodmenulist.common.constants.SecurityConstants;
import com.github.nguyentrucxinh.foodmenulist.dao.ItemDao;
import com.github.nguyentrucxinh.foodmenulist.domain.Item;
import com.github.nguyentrucxinh.foodmenulist.dto.*;
import com.github.nguyentrucxinh.foodmenulist.service.AppEngineMailApiService;
import com.github.nguyentrucxinh.foodmenulist.service.GoogleCloudStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@RestController
@RequestMapping({
        SecurityConstants.API_ADMIN_URL + "/items",
        SecurityConstants.API_USER_URL + "/items"
})
public class ItemController extends GenericControllerImpl<Item> {

    private static final Logger LOGGER = Logger.getLogger(ItemController.class.getName());

    @Autowired
    private ItemDao itemDao;

    @Autowired
    private GoogleCloudStorageService googleCloudStorageService;

    @Autowired
    private AppEngineMailApiService appEngineMailApiService;

    @PostMapping("/upload/{id}")
    public Item upload(@PathVariable Long id, @RequestParam("file") MultipartFile multipartFile, @RequestParam String name, @RequestParam String description) throws IOException {

        UploadResultDto uploadResultDto = googleCloudStorageService.uploadAndGetMediaLink(multipartFile, GoogleCloudStorageConstants.BUCKET_DIRECTORY_IMAGE);

        appEngineMailApiService.sendMail(MailType.SIMPLE, MailDto.builder()
                .recipientDto(RecipientDto.builder().recipientType(RecipientType.TO).address("nguyentrucxjnh@gmail.com").personal("Ms. Dung").build())
                .subject("Subject 1")
                .content("Content 1")
                .build()
        );

        appEngineMailApiService.sendMail(MailType.MULTIPART, MailDto.builder()
                .recipientDto(RecipientDto.builder().recipientType(RecipientType.TO).address("nguyentrucxjnh@gmail.com").personal("Ms. Dung").build())
                .subject("Subject 2")
                .content("<h1>Content 2<h1>")
                .contentType("text/html")
                .fileAttachmentDto(FileAttachmentDto.builder().fileName(multipartFile.getOriginalFilename()).content(multipartFile.getBytes()).contentType(multipartFile.getContentType()).build())
                .build()
        );

        Map<String, Object> dataModel = new HashMap<>();
        dataModel.put("message", "Hello world!");

        appEngineMailApiService.sendMail(MailType.MULTIPART, MailDto.builder()
                .recipientDto(RecipientDto.builder().recipientType(RecipientType.TO).address("nguyentrucxjnh@gmail.com").personal("Ms. Dung").build())
                .subject("Subject 2")
                .contentType("text/html")
                .fileAttachmentDto(FileAttachmentDto.builder().fileName(multipartFile.getOriginalFilename()).content(multipartFile.getBytes()).contentType(multipartFile.getContentType()).build())
                .useTemplate(true)
                .templateDto(TemplateDto.builder().templateName("sign-up.ftl").dataModel(dataModel).build())
                .build()
        );

        Item item;

        if (id <= 0)
            item = new Item();
        else
            item = itemDao.findById(id);

        item.setName(name);
        item.setDescription(description);
        item.setImageUrl(uploadResultDto.getMediaLink());
        item.setCreatedDate(new Date());
        item.setBlobName(uploadResultDto.getBlobName());
        item.setGeneration(uploadResultDto.getGeneration());

        LOGGER.info(item.toString() + " saving...");

        return itemDao.save(item);
    }
}
