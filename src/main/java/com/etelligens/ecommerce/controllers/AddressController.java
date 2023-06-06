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
import com.etelligens.ecommerce.dto.AddressDTO;
import com.etelligens.ecommerce.service.AddressService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/ecommerce")
public class AddressController {

	@Autowired
	AddressService addressService;

	@Autowired
	UserService userService;

	@PostMapping("/createAddress")
	public ResponseEntity<AddressDTO> createAddress(HttpServletRequest request, @RequestBody AddressDTO addressDTO) {
		String userId = userService.getUserName(request);
		AddressDTO savedAddress = addressService.createAddress(userId, addressDTO);
		return new ResponseEntity<>(savedAddress, HttpStatus.OK);
	}

	@GetMapping("/allAddresses")
	public ResponseEntity<List<AddressDTO>> getAllAddresses(HttpServletRequest request) {
		String userId = userService.getUserName(request);
		List<AddressDTO> addresses = addressService.getAllAddresses(userId);

		return new ResponseEntity<>(addresses, HttpStatus.OK);
	}

	@DeleteMapping("/deleteAddress/{addressId}")
	public ResponseEntity<String> deleteAddress(@PathVariable long addressId) {
		addressService.deleteAddress(addressId);

		return new ResponseEntity<>("Address Successfully Deleted", HttpStatus.OK);
	}

	@PutMapping("/updateAddress")
	public ResponseEntity<AddressDTO> updateAddress(@RequestBody AddressDTO address) {
		AddressDTO addressDTO = addressService.updateAddress(address);

		return new ResponseEntity<>(addressDTO, HttpStatus.OK);
	}

}
