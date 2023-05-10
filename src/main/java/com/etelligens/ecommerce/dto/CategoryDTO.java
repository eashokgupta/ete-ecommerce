package com.etelligens.ecommerce.dto;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {

	private Long id;
	
	private List<SlugDTO> slug = new ArrayList<>();
	
	private String name;
	
	private byte[] img;
	
	private String description;

}
