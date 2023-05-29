package com.etelligens.ecommerce.dto;

import java.sql.Date;

import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProfileDTO {
	
	
	private String user;
	private Long profileId;
	private String firstName;
	private String lastName;
	private String gender;
	private Long phoneNumber;
	private String email;
	private String password;
	private Date birthday;
	
	@Lob
	private byte[] profilePhoto;
	
	}
