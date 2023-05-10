package com.etelligens.ecommerce.dto;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
	private int id;

	private Integer quantity;

	private String name;

	private String url;

	private Set<ImagesDTO> images = new HashSet<>();

	private String shortDescription;

	private String description;

	private String sku;

	private String upc;

	private CategoryDTO category;

	private String brand;

	private String label;

	private OfferDTO offer;

	private Double price;

	private Boolean visibility;

	private Boolean b2b;

	private Timestamp createdAt;

	private Timestamp updatedAt;
}
