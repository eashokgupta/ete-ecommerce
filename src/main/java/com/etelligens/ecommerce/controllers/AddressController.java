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

/**
* This Controller will work for create,udate,get and delete address of user.
*/
@RestController
@RequestMapping("/ecommerce")
public class AddressController {

	@Autowired
	AddressService addressService;

	@Autowired
	UserService userService;

	/**
	* This API will work for create the user address
	*/
	@PostMapping("/createAddress")
	public ResponseEntity<AddressDTO> createAddress(HttpServletRequest request, @RequestBody AddressDTO addressDTO) {
		String userId = userService.getUserName(request);
		return new ResponseEntity<>(addressService.createAddress(userId, addressDTO), HttpStatus.OK);
	}

	/**
	* This API will work for get All the user addresses 
	*/
	@GetMapping("/allAddresses")
	public ResponseEntity<List<AddressDTO>> getAllAddresses(HttpServletRequest request) {
		String userId = userService.getUserName(request);
		return new ResponseEntity<>(addressService.getAllAddresses(userId), HttpStatus.OK);
	}

	/**
	* This API will work for delete the user address by id.
	*/
	@DeleteMapping("/deleteAddress/{addressId}")
	public ResponseEntity<String> deleteAddress(@PathVariable long addressId) {
		return new ResponseEntity<>(addressService.deleteAddress(addressId), HttpStatus.OK);
	}
	/**
	* This API will work for Update the user address
	*/
	@PutMapping("/updateAddress")
	public ResponseEntity<AddressDTO> updateAddress(HttpServletRequest request, @RequestBody AddressDTO address) {
		return new ResponseEntity<>(addressService.updateAddress(address), HttpStatus.OK);
	}
    
	/**
	* This API will work for add the location.
	*/
	@PostMapping("/addLocation")
	public ResponseEntity<LocationDTO> addLocation(@RequestBody LocationDTO locationDTO) {
		return new ResponseEntity<>(addressService.addLocation(locationDTO), HttpStatus.OK);
	}

	/**
	* This API will work for get City from State and Country.
	*/
	@GetMapping("/getCity/{state}/{country}")
	public ResponseEntity<List<String>> getCity(@PathVariable String state, @PathVariable String country) {
		return new ResponseEntity<>(addressService.getCity(state), HttpStatus.OK);
	}

	/**
	* This API will work for get State from country.
	*/
	@GetMapping("/getState/{country}")
	public ResponseEntity<List<String>> getState(@PathVariable String country) {
		return new ResponseEntity<>(addressService.getState(country), HttpStatus.OK);
	}
	
	/**
	* This API will work for get country.
	*/

	@GetMapping("/getCountry")
	public ResponseEntity<List<String>> getCountry() {
		return new ResponseEntity<>(addressService.getCountry(), HttpStatus.OK);
	}
}
