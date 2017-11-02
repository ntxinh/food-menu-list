package com.github.nguyentrucxinh.foodmenulist.api;

import java.util.List;

public interface GenericController<E> {

    List<E> findAll();

    E findById(Long id);

    E create(E t);

    E update(Long id, E t);

    void delete(Long id);
}
