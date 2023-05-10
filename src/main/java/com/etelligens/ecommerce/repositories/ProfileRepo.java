package com.etelligens.ecommerce.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.etelligens.ecommerce.model.Profile;

@Component
public interface ProfileRepo extends JpaRepository<Profile, Integer> {
	public Optional<Profile> findById(Integer id);

}