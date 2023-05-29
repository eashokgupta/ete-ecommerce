package com.etelligens.ecommerce.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etelligens.ecommerce.dto.CartDTO;
import com.etelligens.ecommerce.model.Cart;
import com.etelligens.ecommerce.repositories.CartRepo;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	CartRepo cartRepo;

	@Autowired
	ModelMapper mapper;

	@Override
	public List<CartDTO> getAllCartItem(String userId) {

		return mapper.map(cartRepo.findByUserId(userId), new TypeToken<List<CartDTO>>() {
		}.getType());
	}

	@Override
	public CartDTO addToCart(CartDTO cart) {
		Cart addItemToCart = mapper.map(cart, Cart.class);
		return mapper.map(cartRepo.save(addItemToCart), CartDTO.class);
	}

	@Override
		public String deleteCartProductById(String userId, Long id) {
			cartRepo.deleteByIdAndUserId(userId,id);
			Boolean flag = cartRepo.findById(id).isEmpty();
			if (Boolean.TRUE.equals(flag)) {
				return "Item Successfully Deleted";
			}
			return "Not Deleted";
		}
	

	@Override
	public CartDTO updateCart(CartDTO cart) {
	Boolean cart1 =	cartRepo.findByUserId(cart.getUserId()).isEmpty();
	if(Boolean.FALSE.equals(cart1)) {
		Cart updateCart = mapper.map(cart, Cart.class);
		updateCart = cartRepo.save(updateCart);
		return mapper.map(updateCart, CartDTO.class);
	}
	return null;
	}

	@Override
	public CartDTO getCartProductById(Long productId) {
		return mapper.map(cartRepo.findById(productId).orElseThrow(), CartDTO.class);
	}
}
