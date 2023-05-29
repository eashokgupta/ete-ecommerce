package com.etelligens.ecommerce.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.etelligens.ecommerce.model.Cart;

@Repository
public interface CartRepo  extends JpaRepository<Cart, Long>{
	
	public Optional<Cart> findByUserId(String productId);
	
	public Optional<Cart>  findById(Long id);

	public void deleteByIdAndUserId(String userId, Long id);
}
