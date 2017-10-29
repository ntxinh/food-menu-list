package com.github.nguyentrucxinh.foodmenulist.repository;

import java.util.List;

public interface BaseRepository<T> {

    List<T> findAll();

    T findById(Long id);

    T create(T t);

    void update(Long id, T t);

    void delete(Long id);

}
