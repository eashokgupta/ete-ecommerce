package com.etelligens.ecommerce.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.etelligens.ecommerce.auth.exception.CategoryNotFoundException;
import com.etelligens.ecommerce.dto.CategoryDTO;
import com.etelligens.ecommerce.dto.ImagesDTO;
import com.etelligens.ecommerce.dto.SlugDTO;
import com.etelligens.ecommerce.model.Category;
import com.etelligens.ecommerce.model.Images;
import com.etelligens.ecommerce.model.Slug;
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

		Category cate = mapper.map(category, Category.class);
		return mapper.map(categoryRepository.save(cate), CategoryDTO.class);
	}

	@Override
	public CategoryDTO addSlug(Long id, SlugDTO slug) {
		Category category = categoryRepository.getReferenceById(id);
		
			if (category.getId() != null) {
				Slug sg = mapper.map(slug, Slug.class);
				category.getSlug().add(sg);
				category = categoryRepository.save(category);
			}else {
				throw new CategoryNotFoundException("This Category Not Exist");
			}
		
		return mapper.map(category, CategoryDTO.class);
	}

}
