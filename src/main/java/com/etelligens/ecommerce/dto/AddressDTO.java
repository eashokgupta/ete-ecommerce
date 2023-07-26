package com.etelligens.ecommerce.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddressDTO {


	private Long id;
	private String firstName;
	private String lastName;
	private String streetAddress;
	private String streetAddress1;
	private Long phoneNumber;
	private String city;
	private String stateProvinceRegion;
	private String countryRegion;
	private Integer zipcode;
	private String type;

}

