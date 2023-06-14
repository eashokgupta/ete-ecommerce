package com.etelligens.ecommerce.repositories;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.etelligens.ecommerce.model.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {

	@Query(value = "Select city FROM Location WHERE state_province_region=:state",nativeQuery = true)
	List<String> getCity(@Param("state") String state);
	
    @Query(value = "Select state_province_region FROM Location WHERE country_region=:country",nativeQuery = true)
	List<String> getState(@Param("country") String country);
    
    @Query(value = "Select country_region FROM Location",nativeQuery = true)
	List<String> getCountry();
}
