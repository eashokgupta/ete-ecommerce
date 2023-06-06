package com.etelligens.ecommerce.model;

import java.io.Serializable;
import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Profile")
public class Profile implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2418408984979803239L;
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long profileId;
	private String firstName;
	private String lastName;
	private String gender;
	private Long phoneNumber;
	private String email;
	private String password;
	private Date birthday;
	
	@Lob
	@Column(columnDefinition = "LONGBLOB")
	private byte[] profilePhoto;
	
	
	

}

