package com.etelligens.ecommerce.repositories;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.etelligens.ecommerce.model.Address;

@Repository
public interface AddressRepo extends JpaRepository<Address, Long> {
	public List<Address> findAll();

	public Optional<Address> findById(long addressId);

}
