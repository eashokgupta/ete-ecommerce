package com.etelligens.ecommerce.dto;

import java.util.List;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetailsDTO{

	private Long id;

	private String category;

	private List<String> color;

	private List<byte[]> images;
	
	private List<String> size;
	
	private List<ReviewDTO> reviews;

}
