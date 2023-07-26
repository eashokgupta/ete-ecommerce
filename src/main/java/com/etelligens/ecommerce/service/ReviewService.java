package com.etelligens.ecommerce.service;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.etelligens.ecommerce.dto.ReviewDTO;

@Component
public interface ReviewService {
	
	public ReviewDTO addReview(String userId, String review, MultipartFile[] files );
	
	public ReviewDTO updateReview(String userId, String review, MultipartFile[] files);
	
	public List<ReviewDTO> getAll(Long productId);
	
	public String deleteReview(Long id);

	public String deleteReviewImages(Long imageId);

}
