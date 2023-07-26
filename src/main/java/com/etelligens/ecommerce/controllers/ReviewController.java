package com.etelligens.ecommerce.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.etelligens.ecommerce.auth.service.UserService;
import com.etelligens.ecommerce.dto.ReviewDTO;
import com.etelligens.ecommerce.service.ReviewService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/ecommerce/reviews")
public class ReviewController {

	@Autowired
	private ReviewService reviewService;

	@Autowired
	UserService userService;

	@PostMapping("/createreview")
	public ResponseEntity<ReviewDTO> createReview(HttpServletRequest request, @RequestParam("review") String review,
			@RequestParam("imgs") MultipartFile[] files) {
		String userId = userService.getUserName(request);
		return ResponseEntity.status(HttpStatus.OK).body(reviewService.addReview(userId, review, files));
	}

	@PutMapping("/{id}")
	public ResponseEntity<ReviewDTO> updateReview(HttpServletRequest request, @RequestParam("review") String review,
			@RequestParam("imgs") MultipartFile[] files) {
		String userId = userService.getUserName(request);
		return new ResponseEntity<>(reviewService.updateReview(userId, review, files), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteReviewById(@PathVariable("id") Long reviewId) {
		return new ResponseEntity<>(reviewService.deleteReview(reviewId), HttpStatus.OK);
	}

	@GetMapping("/{productId}/getAll")
	public ResponseEntity<List<ReviewDTO>> getAllReviews(@PathVariable Long productId) {
		return new ResponseEntity<>(reviewService.getAll(productId), HttpStatus.OK);
	}
	
	@DeleteMapping("delete-reviewImages/{id}")
	public ResponseEntity<String> deleteReviewImages(@PathVariable("id") Long imageId){
		return new ResponseEntity<>(reviewService.deleteReviewImages(imageId), HttpStatus.OK);
	}

}
