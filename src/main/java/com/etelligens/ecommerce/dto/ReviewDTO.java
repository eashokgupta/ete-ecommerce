package com.etelligens.ecommerce.dto;

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
	
	private byte[] userPhoto;
	
	private List<ReviewImagesDTO> reviewImages;
	
	private String username;
	
	private Long productId;

}
