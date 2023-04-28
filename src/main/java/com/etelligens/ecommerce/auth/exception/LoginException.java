package com.etelligens.ecommerce.auth.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.NOT_FOUND , reason="Username or Password incorrect")
public class LoginException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6164134112282764852L;
	
	public LoginException(String exceptionMessage) {
		super(exceptionMessage);
	}

}
