package com.etelligens.ecommerce.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.etelligens.ecommerce.model.Cart;

@Repository
public interface CartRepository  extends JpaRepository<Cart, Long>{
	
	public List<Cart> findByUserEmail(String productId);
	
	public Optional<Cart>  findById(Long id);

	public void deleteByIdAndUserId(String userId, Long id);
}
