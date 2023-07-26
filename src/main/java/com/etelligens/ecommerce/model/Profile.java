package com.etelligens.ecommerce.model;

import java.io.Serializable;
import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.etelligens.ecommerce.auth.model.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToOne;
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
public class Profile implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2418408984979803239L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String firstName;
	private String lastName;
	private String gender;
	private Long phoneNumber;
	private String email;

	@Column(columnDefinition = "DATE")
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private LocalDate birthday;

	@Lob
	@Column(columnDefinition = "LONGBLOB")
	private byte[] profilePhoto;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", referencedColumnName = "email")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private User user;

}
