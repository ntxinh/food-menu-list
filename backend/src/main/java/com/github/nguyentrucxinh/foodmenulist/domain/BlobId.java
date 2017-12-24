package com.github.nguyentrucxinh.foodmenulist.domain;

import com.googlecode.objectify.annotation.Entity;
import lombok.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class BlobId extends BaseEntity {

    private String bucket;
    private String name;
    private Long generation;
}
