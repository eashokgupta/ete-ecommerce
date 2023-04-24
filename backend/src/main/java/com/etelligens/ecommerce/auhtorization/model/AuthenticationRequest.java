package com.etelligens.ecommerce.auhtorization.model;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Component
public class AuthenticationRequest {
	
	/**
	* This will be the username
	*/ 
		private String username;
	/**
	* This will be the password
	*/ 
		private String password;

}
