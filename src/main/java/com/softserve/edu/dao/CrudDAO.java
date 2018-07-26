package com.softserve.edu.dao;

import com.softserve.edu.entity.IEntity;

public interface CrudDAO<E extends IEntity> extends ReadDAO<E> {

    E add(E element);

    void update(E element);

    void delete(E element);

    void deleteById(Long id);

}
