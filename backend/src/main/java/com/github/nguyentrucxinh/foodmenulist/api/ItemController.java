package com.github.nguyentrucxinh.foodmenulist.api;

import com.github.nguyentrucxinh.foodmenulist.dao.ItemDao;
import com.github.nguyentrucxinh.foodmenulist.domain.Item;
import com.github.nguyentrucxinh.foodmenulist.service.CloudStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@RestController
@RequestMapping("/api/items")
public class ItemController extends GenericControllerImpl<Item> {

    @Autowired
    private ItemDao itemDao;

    @Autowired
    private Environment environment;

    @Autowired
    private CloudStorageService cloudStorageService;

    @PostMapping("/upload/{id}")
    public Item save(@PathVariable Long id, @RequestParam("file") MultipartFile multipartFile) {

        String imageUrl = cloudStorageService.getImageUrl(multipartFile, environment.getActiveProfiles());

        Item item = itemDao.findById(id);
        System.out.println(imageUrl);
        item.setImageUrl(imageUrl);
        item.setCreatedDate(new Date());

        return itemDao.save(item);
    }
}
