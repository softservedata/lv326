package com.softserve.edu;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//@RestController
@Controller
public class WelcomeController {

	// inject via application.properties
	@Value("${welcome.message:test}")
	private String message = "Hello World";

	@RequestMapping("/app")
	public String welcome(Map<String, Object> model) {
		// Running Methods of Service Classes
		System.out.println("+++++message: " + message);
		model.put("message", this.message);
		return "welcome";
	}

	@RequestMapping("/")
	public String welcome2(Map<String, Object> model) {
		// Running Methods of Service Classes
		System.out.println("+++++message: " + message);
		model.put("message", this.message);
		return "welcome";
	}

}
