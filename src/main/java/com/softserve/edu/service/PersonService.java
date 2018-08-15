package com.softserve.edu.service;

import java.util.List;

import com.softserve.edu.entity.Person;

public interface PersonService {
	
	List<Person> findByName(String name);
	
}
