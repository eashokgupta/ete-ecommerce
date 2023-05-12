package com.etelligens.ecommerce.auth.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7134041123389439881L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name="name", nullable = false)
	private String name;
	
	@Column(name="email", nullable = false, unique = true)
	private String email;
	
	private int contactNumber;
	
	private String providerId;
	
	@Column(name="password", nullable = false)
	private String password;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name="user_role", joinColumns = @JoinColumn(name = "user",referencedColumnName ="id"),
	inverseJoinColumns = @JoinColumn(name = "role" , referencedColumnName = "id"))
	private Set<Role> roles = new HashSet<>();
}

