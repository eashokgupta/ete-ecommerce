package com.etelligens.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponseDTO {

	private Long id;
	
	private String name;
	
	private Double discountAmount;

	private String discountType;
	
	private float rating;
	
	private Double priceAfterDiscount;
	
	private String brand;
	
	private byte[] productImage;
	
	
}
