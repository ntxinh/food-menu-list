package com.github.nguyentrucxinh.foodmenulist.domain;

import com.googlecode.objectify.annotation.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Task extends BaseEntity {

	private String description;
}
