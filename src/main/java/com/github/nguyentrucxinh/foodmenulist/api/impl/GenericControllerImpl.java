package com.github.nguyentrucxinh.foodmenulist.api.impl;


import com.github.nguyentrucxinh.foodmenulist.api.GenericController;
import com.github.nguyentrucxinh.foodmenulist.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public abstract class GenericControllerImpl<E> implements GenericController<E> {

    @Autowired
    private GenericService<E> genericService;

    @GetMapping
    @Override
    public List<E> findAll() {
        return genericService.findAll();
    }

    @GetMapping("/{id}")
    @Override
    public E findById(@PathVariable Long id) {
        return genericService.findById(id);
    }

    @PostMapping()
    @Override
    public E save(@RequestBody E t) {
        return genericService.save(t);
    }

    @DeleteMapping("/{id}")
    @Override
    public void delete(@PathVariable Long id) {
        genericService.delete(id);
    }
}
