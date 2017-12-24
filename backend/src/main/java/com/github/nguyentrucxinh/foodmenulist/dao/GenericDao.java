package com.github.nguyentrucxinh.foodmenulist.dao;

import java.util.List;

public interface GenericDao<E> {

    List<E> findAll();

    E findById(Long id);

    E save(E t);

    void deleteById(Long id);
}

