package com.github.nguyentrucxinh.foodmenulist.repository;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import static com.googlecode.objectify.ObjectifyService.ofy;

public abstract class ObjectifyGenericRepository<T> {

    /* CLASS */
    private Class<T> persistenceClass = null;

    @SuppressWarnings("unchecked")
    public ObjectifyGenericRepository() {
        ParameterizedType pt = (ParameterizedType) getClass().getGenericSuperclass();
        persistenceClass = (Class<T>) pt.getActualTypeArguments()[0];
    }

    public Class<T> getPersistenceClass() {
        return persistenceClass;
    }

    /* METHOD */
    public T create(T t) {
        ofy().save().entity(t).now();
        return t;
    }

    public List<T> findAll() {
        return ofy()
                .load()
                .type(getPersistenceClass())
                .list();
    }

    public T findById(Long id) {
        return ofy().load().type(getPersistenceClass()).id(id).now();
    }

    public void update(Long id, T t) {
        ofy().save().entity(t).now();
    }

    public void delete(Long id) {
        T t = ofy().load().type(getPersistenceClass()).id(id).now();
        ofy().delete().entity(t).now();
    }
}