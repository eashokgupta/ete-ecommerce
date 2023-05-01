package com.etelligens.ecommerce.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {
	
	private String token;
	
	private String username;
	
	private String emailId;
	
	private int contactNumber;

}
