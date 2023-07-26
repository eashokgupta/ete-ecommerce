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
import com.etelligens.ecommerce.dto.LocationDTO;
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
		return new ResponseEntity<>(addressService.createAddress(userId, addressDTO), HttpStatus.OK);
	}

	@GetMapping("/allAddresses")
	public ResponseEntity<List<AddressDTO>> getAllAddresses(HttpServletRequest request) {
		String userId = userService.getUserName(request);
		return new ResponseEntity<>(addressService.getAllAddresses(userId), HttpStatus.OK);
	}

	@DeleteMapping("/deleteAddress/{addressId}")
	public ResponseEntity<String> deleteAddress(@PathVariable long addressId) {
		return new ResponseEntity<>(addressService.deleteAddress(addressId), HttpStatus.OK);
	}

	@PutMapping("/updateAddress")
	public ResponseEntity<AddressDTO> updateAddress(HttpServletRequest request, @RequestBody AddressDTO address) {
		return new ResponseEntity<>(addressService.updateAddress(address), HttpStatus.OK);
	}

	@PostMapping("/addLocation")
	public ResponseEntity<LocationDTO> addLocation(@RequestBody LocationDTO locationDTO) {
		return new ResponseEntity<>(addressService.addLocation(locationDTO), HttpStatus.OK);
	}

	@GetMapping("/getCity/{state}/{country}")
	public ResponseEntity<List<String>> getCity(@PathVariable String state, @PathVariable String country) {
		return new ResponseEntity<>(addressService.getCity(state), HttpStatus.OK);
	}

	@GetMapping("/getState/{country}")
	public ResponseEntity<List<String>> getState(@PathVariable String country) {
		return new ResponseEntity<>(addressService.getState(country), HttpStatus.OK);
	}

	@GetMapping("/getCountry")
	public ResponseEntity<List<String>> getCountry() {
		return new ResponseEntity<>(addressService.getCountry(), HttpStatus.OK);
	}
}
