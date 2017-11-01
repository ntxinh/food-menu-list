package com.github.nguyentrucxinh.foodmenulist.service;

import java.util.List;

public interface GenericService<E> {

    List<E> findAll();

    E findById(Long id);

    E save(E t);

    void delete(Long id);
}
