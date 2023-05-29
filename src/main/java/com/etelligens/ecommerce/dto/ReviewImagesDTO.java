package com.etelligens.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewImagesDTO {
	
	private Long id;
	private byte[] img;
	

}
