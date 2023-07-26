package com.etelligens.ecommerce.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.etelligens.ecommerce.auth.model.User;
import com.etelligens.ecommerce.auth.repositories.UserRepository;
import com.etelligens.ecommerce.dto.ReviewDTO;
import com.etelligens.ecommerce.dto.ReviewImagesDTO;
import com.etelligens.ecommerce.model.Product;
import com.etelligens.ecommerce.model.Review;
import com.etelligens.ecommerce.model.ReviewImages;
import com.etelligens.ecommerce.repositories.ProductRepository;
import com.etelligens.ecommerce.repositories.ProfileRepository;
import com.etelligens.ecommerce.repositories.ReviewImagesRepository;
import com.etelligens.ecommerce.repositories.ReviewRepository;
import com.etelligens.ecommerce.utils.StringToObjectConvert;

@Service
public class ReviewServiceImpl implements ReviewService {

	@Autowired
	ReviewRepository reviewRepo;
	
	@Autowired
	ReviewImagesRepository reviewImagesRepository;

	@Autowired
	ModelMapper mapper;

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ProfileRepository profileRepository;

	@Autowired
	ProductRepository productRepo;

	@Autowired
	StringToObjectConvert stringToObjectConvert;

	@Override
	public ReviewDTO addReview(String userId, String review, MultipartFile[] files) {
		ReviewDTO reviewDTO = stringToObjectConvert.convertJsonToObject(review, ReviewDTO.class);
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
		String userName = null;
		if (user.isPresent() && product.isPresent()) {
			createReview.setUser(user.get());
			createReview.setProduct(product.get());
			userName = user.get().getName();
			createReview.setUserPhoto(profileRepository.findByUserEmail(userId).get().getProfilePhoto());
		}
		ReviewDTO reviewDTO2 = mapper.map(reviewRepo.save(createReview), ReviewDTO.class);
		reviewDTO2.setUsername(userName);
		updateProductRating(product.get());
		return reviewDTO2;
	}

	@Override
	public ReviewDTO updateReview(String userId, String review, MultipartFile[] files) {
		ReviewDTO reviewDTO = stringToObjectConvert.convertJsonToObject(review, ReviewDTO.class);
		Long id = reviewDTO.getId();
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
		
		Review updateReview = mapper.map(reviewDTO, Review.class);
		String userName = null;
		if (user.isPresent() && product.isPresent()) {
			updateReview.setUser(user.get());
			updateReview.setProduct(product.get());
			userName = user.get().getName();
			updateReview.setUserPhoto(profileRepository.findByUserEmail(userId).get().getProfilePhoto());
		}
		ReviewDTO reviewDTO2 = mapper.map(reviewRepo.save(updateReview), ReviewDTO.class);
		reviewDTO2.setUsername(userName);
		updateProductRating(product.get());
		return reviewDTO2;

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

	private Product updateProductRating(Product product) {
		List<ReviewDTO> reviewdto = mapper.map(reviewRepo.findByProductId(product.getId()),
				new TypeToken<List<ReviewDTO>>() {
				}.getType());

		float rating = reviewdto.stream().collect(Collectors.averagingDouble(ReviewDTO::getRating)).floatValue();
		product.setRating(rating);
		productRepo.save(product);
		return product;
	}

	@Override
	public String deleteReviewImages(Long imageId) {
		reviewImagesRepository.deleteById(imageId);
		Boolean flag = reviewImagesRepository.findById(imageId).isEmpty();
		if(flag) {
			return "Removed";
		}
		return "Not Removed";
	}
}
