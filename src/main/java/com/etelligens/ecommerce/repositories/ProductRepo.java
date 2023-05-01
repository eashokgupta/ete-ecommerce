package com.etelligens.ecommerce.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.etelligens.ecommerce.model.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {

	public Optional<Product> findByProductId(Integer productId);

}