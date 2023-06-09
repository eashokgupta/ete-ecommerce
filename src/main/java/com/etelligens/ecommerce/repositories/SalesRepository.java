package com.etelligens.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.etelligens.ecommerce.model.Sales;

@Repository
public interface SalesRepository extends JpaRepository<Sales, Long>{
	
	public Sales findByTitle(String title);

}
