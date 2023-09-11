package com.etelligens.ecommerce.model;

import java.io.Serializable;

import com.etelligens.ecommerce.auth.model.User;
import com.etelligens.ecommerce.dto.AddressDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "Address")
@Builder
public class Address implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7556208287679752588L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String firstName;
	private String lastName;
	private String streetAddress;
	private String streetAddress2;
	private Long phoneNumber;
	private String city;
	private String stateProvinceRegion;
	private String countryRegion;
	private Integer zipcode;
	private String type;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_email", nullable = false, referencedColumnName = "email")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private User user;

	private Orders orders;

}
