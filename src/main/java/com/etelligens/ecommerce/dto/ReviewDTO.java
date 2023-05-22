package com.etelligens.ecommerce.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDTO {
	
	private Long id;
	
	private String title;
	
	private String description;
	
	private int rating;
	
	private List<byte[]> imgs = new ArrayList<>();
	
	private String username;
	
	private Long productId;

}
