package com.etelligens.ecommerce.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductMetaDataDTO {
	
	private Long id;
	
	private String color;
	
	private List<ImagesDTO> images;
	
	private List<MetaData1DTO> metaData;
	
}
