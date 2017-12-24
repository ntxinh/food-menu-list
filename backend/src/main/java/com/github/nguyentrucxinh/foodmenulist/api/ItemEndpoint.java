package com.github.nguyentrucxinh.foodmenulist.api;

import com.github.nguyentrucxinh.foodmenulist.common.constants.SecurityConstants;
import com.github.nguyentrucxinh.foodmenulist.domain.Item;
import com.github.nguyentrucxinh.foodmenulist.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping({
        SecurityConstants.API_ADMIN_URL + "/items",
        SecurityConstants.API_USER_URL + "/items"
})
public class ItemEndpoint extends GenericEndpointImpl<Item> {

    @Autowired
    private ItemService itemService;

    @PostMapping("/upload/{id}")
    public Item upload(@PathVariable Long id, @RequestParam("file") MultipartFile multipartFile, @RequestParam String name, @RequestParam String description) throws IOException {

        return itemService.save(id, multipartFile, name, description);
    }
}
