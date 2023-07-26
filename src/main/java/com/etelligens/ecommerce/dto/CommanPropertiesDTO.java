package com.etelligens.ecommerce.dto;

import java.sql.Timestamp;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class CommanPropertiesDTO {

	private Integer quantity;

	private String name;

	private float rating;

	private String brand;

	private String label;

	private Double price;

	private String discountType;

	private Double discountAmount;
	
	private Double priceAfterDiscount;

	private String shortDescription;

	private String description;

	private List<ReviewDTO> reviews;

	private String sku;

	private String upc;

	private String specification;

	private String url;

	private Boolean visibility;

	private Boolean b2b;

	private Timestamp createdAt;

	private Timestamp updatedAt;

}
