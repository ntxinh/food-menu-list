package com.github.nguyentrucxinh.foodmenulist.api;

import java.util.List;

public interface GenericController<E> {

    List<E> findAll();

    E findById(Long id);

    E save(E t);

    void delete(Long id);
}
