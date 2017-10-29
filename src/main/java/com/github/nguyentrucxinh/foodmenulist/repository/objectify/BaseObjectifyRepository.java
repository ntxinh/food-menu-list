package com.github.nguyentrucxinh.foodmenulist.repository.objectify;

import com.github.nguyentrucxinh.foodmenulist.repository.BaseRepository;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import static com.googlecode.objectify.ObjectifyService.ofy;

public abstract class BaseObjectifyRepository<T> implements BaseRepository<T> {

    /* CLASS */
    private Class<T> persistenceClass = null;

    @SuppressWarnings("unchecked")
    public BaseObjectifyRepository() {
        ParameterizedType pt = (ParameterizedType) getClass().getGenericSuperclass();
        persistenceClass = (Class<T>) pt.getActualTypeArguments()[0];
    }

    public Class<T> getPersistenceClass() {
        return persistenceClass;
    }

    /* METHOD */
    @Override
    public List<T> findAll() {
        return ofy()
                .load()
                .type(getPersistenceClass())
                .list();
    }

    @Override
    public T findById(Long id) {
        return ofy().load().type(getPersistenceClass()).id(id).now();
    }

    @Override
    public T create(T t) {
        ofy().save().entity(t).now();
        return t;
    }

    @Override
    public void update(Long id, T t) {
        ofy().save().entity(t).now();
    }

    @Override
    public void delete(Long id) {
        T t = ofy().load().type(getPersistenceClass()).id(id).now();
        ofy().delete().entity(t).now();
    }
}