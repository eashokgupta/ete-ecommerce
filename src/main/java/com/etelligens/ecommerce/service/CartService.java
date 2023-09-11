package com.etelligens.ecommerce.service;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.etelligens.ecommerce.dto.CartDTO;
import com.etelligens.ecommerce.dto.ResponseCartDTO;

@Component
@Service
public interface CartService {
	public CartDTO addToCart(String userId, CartDTO cart);

	public String deleteCartProductById(String userId,Long id);

	public CartDTO updateCart(String userId, CartDTO cart);

	public List<ResponseCartDTO> getAllCartItem(String userId);

	public ResponseCartDTO getCartProductById(String userId, Long id);
	
	
}