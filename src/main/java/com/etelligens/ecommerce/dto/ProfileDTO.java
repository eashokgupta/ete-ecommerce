package com.etelligens.ecommerce.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

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

	private Long id;
	private String firstName;
	private String lastName;
	private String gender;
	private Long phoneNumber;
	private String email;
	private String password;

	@JsonFormat(pattern = "dd-MM-yyyy")
	private LocalDate birthday;

	@Lob
	private byte[] profilePhoto;

}
