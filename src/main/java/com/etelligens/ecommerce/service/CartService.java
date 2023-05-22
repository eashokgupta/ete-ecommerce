package com.etelligens.ecommerce.service;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.etelligens.ecommerce.dto.CartDTO;

@Component
@Service
public interface CartService {
	public CartDTO addToCart(CartDTO cart);

	public String deleteCartProductById(Long id);

	public CartDTO updateCart(CartDTO cart);

	public List<CartDTO> getAllCartItem();

	public CartDTO getCartProductById(int productId);
	
	
}