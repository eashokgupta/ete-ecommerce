package com.etelligens.ecommerce.dto;

import java.sql.Blob;
import java.sql.Timestamp;
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
	private Integer id;
	private Integer categoryId;
	private Integer quantity;
	private String name;
	private String url;
	private Set<Blob> image;
	private String shortDescription;
	private String description;
	private String sku;
	private String upc;
	private String category;
	private String label;
	private Double price;
	private Boolean visibility;
	private Boolean b2b;
	private Timestamp createdAt;
	private Timestamp updatedAt;
}
