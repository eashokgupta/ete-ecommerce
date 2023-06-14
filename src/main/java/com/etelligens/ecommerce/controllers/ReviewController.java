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
import com.google.gson.Gson;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/toAll/reviews")
public class ReviewController {

	@Autowired
	private ReviewService reviewService;

	@Autowired
	UserService userService;

	@PostMapping("/createreview")
	public ResponseEntity<ReviewDTO> createReview(HttpServletRequest request,@RequestParam("review") String review,	@RequestParam("imgs") MultipartFile[] files) {
		Gson g = new Gson();
		ReviewDTO reviewdto = g.fromJson(review, ReviewDTO.class);
		String userId = userService.getUserName(request);
		ReviewDTO savedReview = reviewService.addReview(userId, reviewdto, files);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedReview);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ReviewDTO> updateReview(@RequestParam("review") String review,	@RequestParam("imgs") MultipartFile[] files) {
		Gson g = new Gson();
		ReviewDTO reviewdto = g.fromJson(review, ReviewDTO.class);
		ReviewDTO updatedReview = reviewService.updateReview(reviewdto, files);
		return ResponseEntity.ok(updatedReview);
	}

	@DeleteMapping("/{id}")
	public String deleteReviewById(@PathVariable("id") Long reviewId) {
		reviewService.deleteReview(reviewId);

		return "Deleted Successfully";
	}

	@GetMapping("/{productId}/getAll")
	public List<ReviewDTO> getAllReviews(@PathVariable Long productId) {
		return reviewService.getAll(productId);
	}

}
