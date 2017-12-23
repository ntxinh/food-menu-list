//package com.github.nguyentrucxinh.foodmenulist.service.impl;
//
//import com.github.nguyentrucxinh.foodmenulist.dao.GenericDao;
//import com.github.nguyentrucxinh.foodmenulist.service.GenericService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class GenericServiceImpl<E> implements GenericService<E> {
//
//    @Autowired
//    private GenericDao<E> genericDao;
//
//    @Override
//    public List<E> findAll() {
//        return genericDao.findAll();
//    }
//
//    @Override
//    public E findById(Long id) {
//        return genericDao.findById(id);
//    }
//
//    @Override
//    public E save(E t) {
//        return genericDao.save(t);
//    }
//
//    @Override
//    public void delete(Long id) {
//        genericDao.delete(id);
//    }
//}
