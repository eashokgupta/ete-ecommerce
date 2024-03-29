package com.etelligens.ecommerce.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.etelligens.ecommerce.auth.model.AuthenticationResponse;

@Component
public class ValidateService {
	
	@Autowired
	private JwtUtil jwtutil;
	
	 /**
	 * @param token
	 * compare the token
	 * returns the authenticationResponse
	 */ 
	public AuthenticationResponse validate(String token) {
		var authenticationResponse = new AuthenticationResponse();
		var jwt = token.substring(7);
		authenticationResponse.setJwtToken(jwt);
		authenticationResponse.setValid(jwtutil.validateToken(jwt));
		return authenticationResponse;
	}

}
