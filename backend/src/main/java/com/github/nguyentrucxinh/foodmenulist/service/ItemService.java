package com.github.nguyentrucxinh.foodmenulist.service;

import com.github.nguyentrucxinh.foodmenulist.domain.Item;
import org.springframework.web.multipart.MultipartFile;

public interface ItemService extends GenericService<Item> {

    Item save(Long id, MultipartFile multipartFile, String name, String description);
}
