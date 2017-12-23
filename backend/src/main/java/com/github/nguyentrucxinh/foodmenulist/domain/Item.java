package com.github.nguyentrucxinh.foodmenulist.domain;

import com.googlecode.objectify.annotation.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    private String blobName;
    private Long generation;
}
