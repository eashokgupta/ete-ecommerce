package com.etelligens.ecommerce.service;

import java.io.IOException;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.etelligens.ecommerce.dto.CategoryDTO;
import com.etelligens.ecommerce.model.Category;
import com.etelligens.ecommerce.repositories.CategoryRepository;
import com.etelligens.ecommerce.repositories.SlugRepository;

@Service
public class CategoriesServiceImpl implements CategoryService {

	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	SlugRepository slugRepository;

	@Autowired
	ModelMapper mapper;

	@Override
	public List<CategoryDTO> getAllCategories() {
		return mapper.map(categoryRepository.findAll(), new TypeToken<List<CategoryDTO>>() {
		}.getType());
	}

	@Override
	public CategoryDTO addCategory(MultipartFile file, CategoryDTO category) throws IOException {

		category.setImg(file.getBytes());
		
		boolean value = categoryRepository.findByName(category.getName()).isEmpty();
		if(value) {
			Category cate = mapper.map(category, Category.class);
			return mapper.map(categoryRepository.save(cate), CategoryDTO.class);
		}
		return null;
	}

}
