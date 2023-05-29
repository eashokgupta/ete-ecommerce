package com.etelligens.ecommerce.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.etelligens.ecommerce.auth.model.User;
import com.etelligens.ecommerce.auth.repositories.UserRepository;
import com.etelligens.ecommerce.dto.ProductDto;
import com.etelligens.ecommerce.dto.ReviewDTO;
import com.etelligens.ecommerce.dto.ReviewImagesDTO;
import com.etelligens.ecommerce.model.Product;
import com.etelligens.ecommerce.model.Review;
import com.etelligens.ecommerce.repositories.ProductRepo;
import com.etelligens.ecommerce.repositories.ReviewRepo;

@Service
public class ReviewServiceImpl implements ReviewService {

	@Autowired
	ReviewRepo reviewRepo;

	@Autowired
	ModelMapper mapper;

	@Autowired
	UserRepository userRepository;

	@Autowired
	ProductRepo productRepo;

	@Override
	public ReviewDTO addReview(String userId, ReviewDTO reviewDTO, MultipartFile[] files) {
		Optional<User> user = userRepository.findByEmail(userId);
		Optional<Product> product = productRepo.findById(reviewDTO.getProductId());
		List<ReviewImagesDTO> reviewImagesDTOs = new ArrayList<>();
		Arrays.asList(files).stream().forEach(file -> {
			try {
				byte[] img = file.getBytes();
				ReviewImagesDTO reviewimage = new ReviewImagesDTO();
				reviewimage.setImg(img);
				reviewImagesDTOs.add(reviewimage);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		reviewDTO.setReviewImages(reviewImagesDTOs);
		Review createReview = mapper.map(reviewDTO, Review.class);
		if (user.isPresent() && product.isPresent()) {
			createReview.setUser(user.get());
			createReview.setProduct(product.get());
		}
		ReviewDTO reviewDTO2 = mapper.map(reviewRepo.save(createReview), ReviewDTO.class);
		reviewDTO2.setUsername(user.get().getName());
		return reviewDTO2;
	}

	@Override
	public ReviewDTO updateReview(ReviewDTO review, MultipartFile[] files) {
		List<ReviewImagesDTO> reviewImagesDTOs = new ArrayList<>();
		Arrays.asList(files).stream().forEach(file -> {
			try {
				byte[] img = file.getBytes();
				ReviewImagesDTO reviewimage = new ReviewImagesDTO();
				reviewimage.setImg(img);
				reviewImagesDTOs.add(reviewimage);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		review.setReviewImages(reviewImagesDTOs);

		Optional<Review> optionalReview = reviewRepo.findById(review.getId());
		if (optionalReview.isPresent()) {

			Review v = optionalReview.get();
			Review review1 = mapper.map(review, v.getClass());
			return mapper.map(reviewRepo.save(review1), ReviewDTO.class);
		}
		return mapper.map(optionalReview, ReviewDTO.class);

	}

	@Override
	public List<ReviewDTO> getAll(Long productId) {

		return mapper.map(reviewRepo.findByProductId(productId), new TypeToken<List<ReviewDTO>>() {
		}.getType());

	}

	@Override
	public String deleteReview(Long id) {
		reviewRepo.deleteById(id);
		return "deleted succesful";

	}

}
