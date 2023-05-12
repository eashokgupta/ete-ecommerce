package com.etelligens.ecommerce.auth.dto;

import java.sql.Blob;
import java.util.HashSet;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
	
	
	private Long id;
	
	
	private String name;
	
	private String email;
	
	private int contactNumber;
	
	private String providerId;
//	
//	private String deviceId;
//	
//	private Blob profilePhoto;
	
	
	private String password;
	
	private Set<RoleDTO> roles = new HashSet<>();

}
