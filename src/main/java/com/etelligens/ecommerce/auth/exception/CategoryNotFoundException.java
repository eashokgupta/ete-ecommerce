package com.etelligens.ecommerce.auth.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.NOT_FOUND ,reason = "Category Not Found")
public class CategoryNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2370377955943275343L;

	public CategoryNotFoundException(String message) {
		super(message);
	}
}
