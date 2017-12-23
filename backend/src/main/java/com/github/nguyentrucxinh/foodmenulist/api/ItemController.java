package com.github.nguyentrucxinh.foodmenulist.api;

import com.github.nguyentrucxinh.foodmenulist.config.GoogleCloudStorageConstants;
import com.github.nguyentrucxinh.foodmenulist.config.SecurityConstants;
import com.github.nguyentrucxinh.foodmenulist.dao.ItemDao;
import com.github.nguyentrucxinh.foodmenulist.domain.Item;
import com.github.nguyentrucxinh.foodmenulist.service.CloudStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@RestController
@RequestMapping({
        SecurityConstants.API_ADMIN_URL + "/items",
        SecurityConstants.API_USER_URL + "/items"
})
public class ItemController extends GenericControllerImpl<Item> {

    @Autowired
    private ItemDao itemDao;

    @Autowired
    private CloudStorageService cloudStorageService;

    @PostMapping("/upload/{id}")
    public Item upload(@PathVariable Long id, @RequestParam("file") MultipartFile multipartFile, @RequestParam String name, @RequestParam String description) {

        String imageUrl = cloudStorageService.uploadAndGetMediaLink(multipartFile, GoogleCloudStorageConstants.BUCKET_DIRECTORY_IMAGE);

        Item item;

        if (id <= 0)
            item = new Item();
        else
            item = itemDao.findById(id);

        item.setName(name);
        item.setDescription(description);
        item.setImageUrl(imageUrl);
        item.setCreatedDate(new Date());

        return itemDao.save(item);
    }

    @PostMapping("/upload-v2/{id}")
    public Item uploadV2(@PathVariable Long id, @ModelAttribute Item item) {

        String imageUrl = cloudStorageService.uploadAndGetMediaLink(item.getFile(), GoogleCloudStorageConstants.BUCKET_DIRECTORY_IMAGE);

        if (id > 0)
            item.setId(id);

        item.setImageUrl(imageUrl);
        item.setCreatedDate(new Date());

        return itemDao.save(item);
    }
}
