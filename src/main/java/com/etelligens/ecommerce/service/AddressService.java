package com.etelligens.ecommerce.service;


import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.etelligens.ecommerce.dto.AddressDTO;


@Service
@Component
public interface AddressService {

	AddressDTO createAddress(String id, AddressDTO addressDTO);

	List<AddressDTO> getAllAddresses(String userId);

	AddressDTO updateAddress(AddressDTO address);

	public String deleteAddress(Long addressId);
}

