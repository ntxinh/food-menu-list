package com.github.nguyentrucxinh.foodmenulist.domain;

import com.googlecode.objectify.annotation.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
class BaseEntity {

    @Id
    private Long id;

}
