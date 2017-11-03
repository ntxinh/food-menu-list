package com.github.nguyentrucxinh.foodmenulist.api;

import com.github.nguyentrucxinh.foodmenulist.dao.ItemDao;
import com.github.nguyentrucxinh.foodmenulist.domain.Item;
import com.github.nguyentrucxinh.foodmenulist.helper.CloudStorageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.Date;

@RestController
@RequestMapping("/api/items")
public class ItemController extends GenericControllerImpl<Item> {

    @Autowired
    private ItemDao itemDao;

    @PostMapping("/save")
    public Item save(@RequestParam("file") MultipartFile multipartFile) {
        CloudStorageHelper storageHelper = new CloudStorageHelper();
        try {
            String imageUrl = storageHelper.getImageUrl(multipartFile);
            System.out.println(imageUrl);
            Item item = new Item();
            item.setImageUrl(imageUrl);
            item.setCreatedDate(new Date());
            return itemDao.save(item);
        } catch (IOException | ServletException e) {
            e.printStackTrace();
        }
        return null;
    }
}
