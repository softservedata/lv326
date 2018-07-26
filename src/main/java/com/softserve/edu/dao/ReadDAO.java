package com.softserve.edu.dao;

import java.util.List;

public interface ReadDAO<E> {
    
    E getById(Long id);

    List<E> getAll();

}
