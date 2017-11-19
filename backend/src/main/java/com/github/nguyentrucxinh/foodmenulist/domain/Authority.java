package com.github.nguyentrucxinh.foodmenulist.domain;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Authority extends BaseEntity {

//    @NotNull
//    private String username;

    private Ref<User> user;

    @NotNull
    private String authority;
}
