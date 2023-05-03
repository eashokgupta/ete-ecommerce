package com.etelligens.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
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
	public List<Cart> getAllCartItem() {

		return cartRepo.findAll();
	}

	@Override
	public CartDTO addToCart(CartDTO cart) {
		Cart addItemToCart = mapper.map(cart, Cart.class);
		return mapper.map(cartRepo.save(addItemToCart), CartDTO.class);
	}

	@Override
		public String deleteCartProductById(int id) {
			cartRepo.deleteById(id);
			Boolean flag = cartRepo.findById(id).isEmpty();
			if (flag) {
				return "Item Successfully Deleted";
			}
			return "Not Deleted";
		}

	

	@Override
	public CartDTO updateCart(CartDTO cart) {
	Boolean cart1 =	cartRepo.findById(cart.getId()).isEmpty();
	Cart updateCart = mapper.map(cart, Cart.class);
	updateCart = cartRepo.save(updateCart);
	return mapper.map(updateCart, CartDTO.class);
	}

	@Override
	public Optional<Cart> getCartProductById(int productId) {
		return cartRepo.findById(productId);
	

	}
}
