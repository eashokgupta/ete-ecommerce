package com.etelligens.ecommerce.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.etelligens.ecommerce.model.Profile;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
	public Optional<Profile> findById(Long id);

}