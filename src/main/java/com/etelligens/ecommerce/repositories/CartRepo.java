package com.etelligens.ecommerce.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.etelligens.ecommerce.model.Cart;

@Component
public interface CartRepo  extends JpaRepository<Cart, Integer>{
	
	public Optional<Cart> findById(Integer productId);
}
