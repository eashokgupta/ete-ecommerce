package com.etelligens.ecommerce.utils;


import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.etelligens.ecommerce.dto.ProductDTO;
import com.etelligens.ecommerce.model.Product;

@Component
public class ProductToProductDTO {
	
	@Autowired
	ModelMapper mapper;
	
	public ProductDTO convertProductToProductDTO(Product product) {
		ProductDTO productDTO = mapper.map(product, ProductDTO.class);
		productDTO.setCategory(product.getCategory().getName());
		
		return productDTO;
	}

	public List<ProductDTO> convertListProductToListProductDTO(List<Product> products) {
		List<ProductDTO> productsDTO = new ArrayList<>();
		for(Product product:products) {
			ProductDTO productDTO = mapper.map(product, ProductDTO.class);
			productDTO.setCategory(product.getCategory().getName());
			productsDTO.add(productDTO);
		}
		return productsDTO;
	}
}
