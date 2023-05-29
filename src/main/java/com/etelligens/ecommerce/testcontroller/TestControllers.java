package com.etelligens.ecommerce.testcontroller;

import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class TestControllers {

	/*
	 * Hello Api For Testing
	 */
	
	@GetMapping
	public LocalDateTime getResult() {
		return LocalDateTime.now();
	}
}
