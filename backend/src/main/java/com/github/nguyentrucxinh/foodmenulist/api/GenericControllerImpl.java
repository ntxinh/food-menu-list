package com.github.nguyentrucxinh.foodmenulist.api;

import com.github.nguyentrucxinh.foodmenulist.dao.GenericDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public abstract class GenericControllerImpl<E> implements GenericController<E> {

    @Autowired
    private GenericDao<E> genericDao;

    @GetMapping
    @Override
    public List<E> findAll() {
        return genericDao.findAll();
    }

    @GetMapping("/{id}")
    @Override
    public E findById(@PathVariable Long id) {
        return genericDao.findById(id);
    }

    @PostMapping
    @Override
    public E create(@RequestBody E t) {
        return genericDao.save(t);
    }

    @PutMapping("/{id}")
    @Override
    public E update(@PathVariable Long id, @RequestBody E t) {
        return genericDao.save(t);
    }

    @DeleteMapping("/{id}")
    @Override
    public void delete(@PathVariable Long id) {
        genericDao.delete(id);
    }
}
