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

import com.etelligens.ecommerce.dto.AddressDTO;
import com.etelligens.ecommerce.service.AddressService;

@RestController
@RequestMapping("/address")
public class AddressController {

	@Autowired
	AddressService addressService;

	@PostMapping("/{id}/createAddress")
	public ResponseEntity<AddressDTO> createAddress(@PathVariable String id,  @RequestBody AddressDTO addressDTO) {
		AddressDTO savedAddress = addressService.createAddress(id ,addressDTO);
        return new ResponseEntity<>(savedAddress, HttpStatus.CREATED);
	}

	@GetMapping("/addresses")
	public ResponseEntity<List<AddressDTO>> findAll() {
		List<AddressDTO> addresses = addressService.getAddresses();

		return new ResponseEntity<>(addresses, HttpStatus.FOUND);
	}

	@GetMapping("/address/{addressId}")
	public ResponseEntity<AddressDTO> getAddress(@PathVariable long addressId) {
		AddressDTO addressDTO = addressService.getAddress(addressId);

		return new ResponseEntity<>(addressDTO, HttpStatus.FOUND);
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

