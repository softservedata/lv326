package com.softserve.edu.dao;

import java.util.List;

import org.hibernate.Session;

import com.softserve.edu.entity.IEntity;

abstract class CrudDAOImpl<E extends IEntity> implements CrudDAO<E> {
    private Class<E> entityClass;
    private Session session;

    public CrudDAOImpl(Class<E> entityClass, Session session) {
        this.entityClass = entityClass;
        this.session = session;
    }

    // @SuppressWarnings("unchecked")
    @Override
    public E getById(Long id) {
        // E element = (E) session.load(entityClass, id); // get proxy
        // E element = (E) session.get(entityClass, id);
    	//
    	session.clear(); // Clear Cache!
        return (E) session.get(entityClass, id);
    }

    //@SuppressWarnings({ "unchecked", "deprecation" })
    @SuppressWarnings({ "unchecked"})
    @Override
    public List<E> getAll() {
        List<E> elements;// = new ArrayList<E>();
        // TODO deprecation
        elements = session.createCriteria(entityClass).list();
        // elements =
        // session.getCriteriaBuilder().createQuery(entityClass).getOrderList();
        return elements;
    }

    @Override
    public E add(E element) {
    	Long id = (Long) session.save(element);
    	element.setId(id);
    	return element;
    }

    @Override
    public void update(E element) {
        session.update(element);
    }

    @Override
    public void delete(E element) {
        session.delete(element);
    }

    @Override
    public void deleteById(Long id) {
        //session.delete(getById(id));
    	delete(getById(id));
    }

}
