package com.etelligens.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.etelligens.ecommerce.dto.CartDTO;
import com.etelligens.ecommerce.model.Cart;

@Component
@Service
public interface CartService {
	public CartDTO addToCart(CartDTO cart);

	public String deleteCartProductById(int id);

	public CartDTO updateCart(CartDTO cart);

	public List<Cart> getAllCartItem();

	public Optional<Cart> getCartProductById(int productId);
	
	
}