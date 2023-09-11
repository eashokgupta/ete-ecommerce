package com.etelligens.ecommerce.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etelligens.ecommerce.auth.model.User;
import com.etelligens.ecommerce.auth.repositories.UserRepository;
import com.etelligens.ecommerce.dto.CartDTO;
import com.etelligens.ecommerce.dto.ResponseCartDTO;
import com.etelligens.ecommerce.model.Cart;
import com.etelligens.ecommerce.repositories.CartRepository;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	CartRepository cartRepository;
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	ModelMapper mapper;

	@Override
	public List<ResponseCartDTO> getAllCartItem(String userId) {
		List<Cart> cart = cartRepository.findAll();
		List<ResponseCartDTO> responseCartDTOList = new ArrayList<>();
		for(Cart ct : cart) {
			ResponseCartDTO cartDto =  mapper.map(ct, ResponseCartDTO.class);
			cartDto.setProductResponseDTO(productService.getProductResponseDTOById(ct.getProductId().getId()));
			responseCartDTOList.add(cartDto);
		}
		return responseCartDTOList;
	}

	@Override
	public CartDTO addToCart(String userId, CartDTO cart) {
		Optional<User> user = userRepository.findByEmail(userId);
		Cart addItemToCart = mapper.map(cart, Cart.class);
		addItemToCart.setUser(user.get());
		addItemToCart.setCreatedAt(LocalDateTime.now());
		addItemToCart.setUpdatedAt(LocalDateTime.now());
		return mapper.map(cartRepository.save(addItemToCart), CartDTO.class);
	}

	@Override
	public String deleteCartProductById(String userId, Long id) {
		cartRepository.deleteById(id);
		Boolean flag = cartRepository.findById(id).isEmpty();
		if (Boolean.TRUE.equals(flag)) {
			return "Item Successfully Deleted";
		}
		return "Not Deleted";
	}

	@Override
	public CartDTO updateCart(String userId, CartDTO cart) {
		Optional<Cart> existingCart = cartRepository.findById(cart.getId());
		userRepository.findByEmail(userId);
		if (!existingCart.isEmpty()) {
			User user1 = existingCart.get().getUser();
			LocalDateTime createdDate = existingCart.get().getCreatedAt();
			Cart updateCart = mapper.map(cart, Cart.class);
			updateCart.setUser(user1);
			updateCart.setUpdatedAt(LocalDateTime.now());
			updateCart.setCreatedAt(createdDate);
			updateCart = cartRepository.save(updateCart);
			return mapper.map(updateCart, CartDTO.class);
		}
		return null;
	}

	@Override
	public ResponseCartDTO getCartProductById(String userId, Long productId) {
		userRepository.findByEmail(userId);
		Cart cart = cartRepository.findById(productId).orElseThrow();
		ResponseCartDTO cartDto =  mapper.map(cart, ResponseCartDTO.class);
		cartDto.setProductResponseDTO(productService.getProductResponseDTOById(productId));
		return cartDto;
	}
}
