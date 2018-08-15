package com.softserve.edu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.softserve.edu.entity.Person;

// Use JPARepository
@Repository
//public interface PersonRepository {//extends CrudRepository<Person, Integer> {
public interface PersonRepository extends JpaRepository<Person, Integer> {
	
	//@Query("select p from Person p where p.name = :name")
	//@Query("select p from Person p")
	List<Person> findByName(
			//@Param("name")
			String name);
	
}
