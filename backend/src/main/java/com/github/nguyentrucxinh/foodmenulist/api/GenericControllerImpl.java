package com.github.nguyentrucxinh.foodmenulist.api;

import com.github.nguyentrucxinh.foodmenulist.dao.GenericDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public abstract class GenericControllerImpl<E> implements GenericController<E> {

    @Autowired
    private GenericDao<E> genericDao;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Override
    public List<E> findAll() {
        return genericDao.findAll();
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Override
    public E findById(@PathVariable Long id) {
        return genericDao.findById(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Override
    public E create(@RequestBody E t) {
        return genericDao.save(t);
    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Override
    public E update(@PathVariable Long id, @RequestBody E t) {
        return genericDao.save(t);
    }

    @DeleteMapping("/{id}")
    @Override
    public void deleteById(@PathVariable Long id) {
        genericDao.deleteById(id);
    }
}
