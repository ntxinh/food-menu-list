package com.github.nguyentrucxinh.foodmenulist.domain;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Ignore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Item extends BaseEntity {

    private String name;
    private String description;
    private String imageUrl;
    private Date createdDate;

    @Ignore
    private MultipartFile file;
}
