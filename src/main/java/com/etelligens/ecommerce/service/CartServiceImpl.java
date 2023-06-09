package com.etelligens.ecommerce.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etelligens.ecommerce.dto.CartDTO;
import com.etelligens.ecommerce.model.Cart;
import com.etelligens.ecommerce.repositories.CartRepository;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	CartRepository cartRepository;

	@Autowired
	ModelMapper mapper;

	@Override
	public List<CartDTO> getAllCartItem(String userId) {

		return mapper.map(cartRepository.findByUserId(userId), new TypeToken<List<CartDTO>>() {
		}.getType());
	}

	@Override
	public CartDTO addToCart(String userId, CartDTO cart) {
		Cart addItemToCart = mapper.map(cart, Cart.class);
		return mapper.map(cartRepository.save(addItemToCart), CartDTO.class);
	}

	@Override
		public String deleteCartProductById(String userId, Long id) {
			cartRepository.deleteByIdAndUserId(userId,id);
			Boolean flag = cartRepository.findById(id).isEmpty();
			if (Boolean.TRUE.equals(flag)) {
				return "Item Successfully Deleted";
			}
			return "Not Deleted";
		}
	

	@Override
	public CartDTO updateCart(String userId, CartDTO cart) {
	Boolean cart1 =	cartRepository.findByUserId(userId).isEmpty();
	if(Boolean.FALSE.equals(cart1)) {
		Cart updateCart = mapper.map(cart, Cart.class);
		updateCart = cartRepository.save(updateCart);
		return mapper.map(updateCart, CartDTO.class);
	}
	return null;
	}

	@Override
	public CartDTO getCartProductById(String userId, Long productId) {
		return mapper.map(cartRepository.findById(productId).orElseThrow(), CartDTO.class);
	}
}
