package com.etelligens.ecommerce.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.etelligens.ecommerce.dto.AddressDTO;
import com.etelligens.ecommerce.dto.LocationDTO;

@Component
public interface AddressService {

	AddressDTO createAddress(String id, AddressDTO addressDTO);

	LocationDTO addLocation(LocationDTO locationDTO);

	List<String> getCity(String state);

	List<String> getState(String country);

	List<String> getCountry();

	List<AddressDTO> getAllAddresses(String userId);

	AddressDTO updateAddress(AddressDTO address);

	public String deleteAddress(Long addressId);
}
