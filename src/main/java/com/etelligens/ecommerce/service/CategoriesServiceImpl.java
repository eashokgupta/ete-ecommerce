package com.etelligens.ecommerce.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

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
		if (value) {
			Category cate = mapper.map(category, Category.class);
			return mapper.map(categoryRepository.save(cate), CategoryDTO.class);
		}
		return null;
	}

	@Override
	public CategoryDTO updateCategory(MultipartFile file, CategoryDTO category) throws IOException {
		if(!file.isEmpty()) {
			category.setImg(file.getBytes());
		}

		Optional<Category> categ= categoryRepository.findByName(category.getName());
		Boolean value = categ.isEmpty();
		if (Boolean.FALSE.equals(value)) {
			Long id = categ.get().getId();
			Category cate = mapper.map(category, Category.class);
			String sentence = category.getName();
			String[] words = sentence.split("\\s+");

	        StringBuilder capitalizedSentence = new StringBuilder();

	        for (String word : words) {
	            String capitalizedWord = word.substring(0, 1).toUpperCase() + word.substring(1);
	            capitalizedSentence.append(capitalizedWord).append(" ");
	        }

	        capitalizedSentence = new StringBuilder(capitalizedSentence.toString().trim());
	        System.out.println(cate.getId());
	        
	        cate.setName(capitalizedSentence.toString());
	        cate.setId(id);
			return mapper.map(categoryRepository.save(cate), CategoryDTO.class);
		}
		return null;
	}

}
