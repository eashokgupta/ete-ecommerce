package com.etelligens.ecommerce.auth.model;

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
public class AuthenticationResponse {
	
	private String jwtToken;
	private Boolean valid;

}
