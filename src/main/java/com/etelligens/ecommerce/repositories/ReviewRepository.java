package com.etelligens.ecommerce.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.etelligens.ecommerce.model.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

	public List<Review> findByProductId(Long productId);

	public Optional<Review> findById(Long id);
}
