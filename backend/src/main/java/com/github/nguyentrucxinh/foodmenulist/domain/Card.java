package com.github.nguyentrucxinh.foodmenulist.domain;

import com.googlecode.objectify.annotation.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Card extends BaseEntity {

    private boolean type; /* 1 for vocab, 2 for code */
    private String front;
    private String back;
    private boolean known;
}
