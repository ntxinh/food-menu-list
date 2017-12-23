package com.github.nguyentrucxinh.foodmenulist.api;

import com.github.nguyentrucxinh.foodmenulist.config.GoogleCloudStorageConstants;
import com.github.nguyentrucxinh.foodmenulist.config.SecurityConstants;
import com.github.nguyentrucxinh.foodmenulist.dao.ItemDao;
import com.github.nguyentrucxinh.foodmenulist.domain.Item;
import com.github.nguyentrucxinh.foodmenulist.service.GoogleCloudStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
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

    @PostMapping("/upload/{id}")
    public Item upload(@PathVariable Long id, @RequestParam("file") MultipartFile multipartFile, @RequestParam String name, @RequestParam String description) {

        String imageUrl = googleCloudStorageService.uploadAndGetMediaLink(multipartFile, GoogleCloudStorageConstants.BUCKET_DIRECTORY_IMAGE);

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

        String imageUrl = googleCloudStorageService.uploadAndGetMediaLink(item.getFile(), GoogleCloudStorageConstants.BUCKET_DIRECTORY_IMAGE);

        item.setId(id > 0 ? id : null);
        item.setImageUrl(imageUrl);
        item.setCreatedDate(new Date());

        LOGGER.info(item.toString() + " saving...");

        return itemDao.save(item);
    }
}
