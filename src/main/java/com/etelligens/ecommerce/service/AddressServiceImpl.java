package com.etelligens.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etelligens.ecommerce.auth.model.User;
import com.etelligens.ecommerce.auth.repositories.UserRepository;
import com.etelligens.ecommerce.dto.AddressDTO;
import com.etelligens.ecommerce.dto.LocationDTO;
import com.etelligens.ecommerce.model.Address;
import com.etelligens.ecommerce.model.Location;
import com.etelligens.ecommerce.repositories.AddressRepository;
import com.etelligens.ecommerce.repositories.LocationRepository;

@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	AddressRepository addressRepo;

	@Autowired
	UserRepository userRepository;

	@Autowired
	LocationRepository locationRepository;

	@Autowired
	ModelMapper mapper;

	@Override
	public AddressDTO createAddress(String id, AddressDTO addressDTO) {
		User user = userRepository.findByEmail(id).orElseThrow();
		Address address = mapper.map(addressDTO, Address.class);
		address.setUser(user);
		Address savedAddress = addressRepo.save(address);
		return mapper.map(savedAddress, AddressDTO.class);
	}

	@Override
	public List<AddressDTO> getAllAddresses(String userId) {

		return mapper.map(addressRepo.findByUserEmail(userId), new TypeToken<List<AddressDTO>>() {
		}.getType());
	}

	@Override
	public AddressDTO updateAddress(AddressDTO address) {
		Optional<Address> existingAddress = addressRepo.findById(address.getId());

		if (!existingAddress.isEmpty()) {
			User user = existingAddress.get().getUser();
			Address updateAddress = mapper.map(address, Address.class);
			updateAddress.setUser(user);
			updateAddress = addressRepo.save(updateAddress);
			return mapper.map(updateAddress, AddressDTO.class);
		}
		return null;
	}

	@Override
	public String deleteAddress(Long addressId) {

		addressRepo.deleteById(addressId);

		Boolean flag = addressRepo.findById(addressId).isEmpty();

		if (Boolean.TRUE.equals(flag)) {
			return "Address Successfully Deleted";
		}
		return "Not Deleted";
	}

	@Override
	public LocationDTO addLocation(LocationDTO locationDTO) {
		Location location = mapper.map(locationDTO, Location.class);
		Location savedLocation = locationRepository.save(location);
		return mapper.map(savedLocation, LocationDTO.class);
	}

	@Override
	public List<String> getCity(String state) {
		return locationRepository.getCity(state);

	}

	@Override
	public List<String> getState(String country) {
		return locationRepository.getState(country);
		
	}

	@Override
	public List<String> getCountry() {
		return locationRepository.getCountry();
	}

}