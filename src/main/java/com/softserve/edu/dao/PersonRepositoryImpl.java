package com.softserve.edu.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.softserve.edu.entity.Person;
import com.softserve.edu.repository.PersonRepository;

//@Repository
public class PersonRepositoryImpl {//implements PersonRepository {

	@PersistenceContext
	private EntityManager entityManager;
	
	public List<Person> findByName(String name) {
		List<?> list = entityManager
				.createQuery("SELECT p FROM Person p")
				.getResultList();
		return (List<Person>) list;
		//return null;
	}
	
}
