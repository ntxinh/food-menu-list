package com.github.nguyentrucxinh.foodmenulist.dao.impl;

import com.github.nguyentrucxinh.foodmenulist.dao.GenericDao;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.Result;
import org.springframework.stereotype.Repository;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import static com.googlecode.objectify.ObjectifyService.ofy;

@Repository
public abstract class GenericDaoImpl<E> implements GenericDao<E> {

    /* CLASS */
    private Class<E> entityClass = null;

    @SuppressWarnings("unchecked")
    public GenericDaoImpl() {
        ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
        entityClass = (Class<E>) genericSuperclass.getActualTypeArguments()[0];
    }

    private Class<E> getEntityClass() {
        return entityClass;
    }

    /* METHOD */
    @Override
    public List<E> findAll() {
        return ofy()
                .load()
                .type(getEntityClass())
                .list();
    }

    @Override
    public E findById(Long id) {
        return ofy().load().type(getEntityClass()).id(id).now();
    }

    @Override
    public E save(E t) {
        // Key
        Result<Key<E>> result = ofy().save().entity(t);
        // Save
        result.now();
        return t;
    }

    @Override
    public void deleteById(Long id) {
        E t = ofy().load().type(getEntityClass()).id(id).now();
        ofy().delete().entity(t).now();
    }
}

