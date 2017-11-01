package com.github.nguyentrucxinh.foodmenulist.domain;

import com.googlecode.objectify.annotation.Id;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
class BaseEntity implements Serializable {

    @Id
    private Long id;

}
