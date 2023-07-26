package com.etelligens.ecommerce.service;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.etelligens.ecommerce.dto.CategoryDTO;

@Component
public interface CategoryService {

	public List<CategoryDTO> getAllCategories();
	
	public CategoryDTO addCategory(MultipartFile file, String name) throws IOException;
	
	public CategoryDTO updateCategory(Long id, MultipartFile file, String name) throws IOException;

}
