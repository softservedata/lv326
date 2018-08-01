package com.softserve.edu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.softserve.edu.entity.Person;
import com.softserve.edu.service.PersonService;

@RestController
public class PersonController {
	
	@Autowired
	private PersonService personService;

	//@GetMapping("/info")
	@GetMapping("/")
	public List<Person> getPersonInfo(
	//public String getPersonInfo(
			@RequestParam(value = "name", required = false, defaultValue = "ivan")
			String name
			) {
		List<Person> persons = personService.findByName(name);
		return persons;
		//return "Ok";
	}
	
	//@RequestMapping("/")
	public Person greeting() {
		// Call classes from service layer
		Person person = new Person();
		person.setName("NAME");
		person.setCity("CITY");
		person.setPid(-1);
		return person;
	}
	
}
