package com.etelligens.ecommerce.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.etelligens.ecommerce.auth.service.UserService;
import com.etelligens.ecommerce.dto.CartDTO;
import com.etelligens.ecommerce.service.CartService;

import jakarta.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/ecommerce")
public class CartController {

	@Autowired
	CartService cartService;
	
	@Autowired
	UserService userService;

	@PostMapping("/addToCart")
	public ResponseEntity<CartDTO> addToCart(@RequestBody CartDTO cart) {
		CartDTO cart2 = cartService.addToCart(cart);
		return new ResponseEntity<>(cart2, HttpStatus.OK);

	}

	@PutMapping("/updateCart")
	public ResponseEntity<CartDTO> updateCart(@RequestBody CartDTO cart) {
		CartDTO updateItem = cartService.updateCart(cart);
		return new ResponseEntity<>(updateItem, HttpStatus.OK);
	}
	@DeleteMapping("/deleteCart/{id}")
	public ResponseEntity<String> deleteProduct(HttpServletRequest request,@PathVariable Long id) {
		String userId = userService.getUserName(request);
		    cartService.deleteCartProductById(userId,id);
		return new ResponseEntity<>("Item Deleted Successfully", HttpStatus.OK);
	}

	@GetMapping("/cart/{id}")
	public CartDTO getAllProductFromCart(@PathVariable Long id) {
		return cartService.getCartProductById(id);
	}

	@GetMapping("/cartItems")
	public ResponseEntity<List<CartDTO>> findAllCartItem(HttpServletRequest request) {
		String userId = userService.getUserName(request);
		List<CartDTO> items = cartService.getAllCartItem(userId);
		return new ResponseEntity<>(items, HttpStatus.OK);

	}

}