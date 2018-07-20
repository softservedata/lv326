package com.softserve.edu.controllers;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.softserve.edu.dto.Greeting;

@RestController
public class GreetingController {
	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();

	//@RequestMapping(value="/greeting", method=RequestMethod.GET)
	//@RequestMapping({"/", "/greeting"}) // GET
	@RequestMapping("/greeting") // GET
	public Greeting greeting(
			@RequestParam(value = "name", required = false, defaultValue = "World")
			String name) {
		// Call classes from service layer
		return new Greeting(counter.incrementAndGet(),
				String.format(template, name));
	}

	//@RequestMapping("/")
	public String greeting() {
		// Call classes from service layer
		return "redirect:/greeting";
	}

}

