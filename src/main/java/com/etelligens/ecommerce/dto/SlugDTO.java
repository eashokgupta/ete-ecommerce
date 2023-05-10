package com.etelligens.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SlugDTO {

	private int id;
	
	private String name;
	
	private String url;
	
	private String description;

}
