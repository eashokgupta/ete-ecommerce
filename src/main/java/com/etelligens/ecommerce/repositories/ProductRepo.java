package com.etelligens.ecommerce.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.etelligens.ecommerce.model.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {

	public Optional<Product> findById(Integer productId);

	@Query(value = "SELECT * FROM product WHERE category_id=:id", nativeQuery = true)
	public List<Product> findAllByCategoryId(Long id);

	@Query("""
			    SELECT p FROM Product p LEFT JOIN p.productMetaData pm LEFT JOIN pm.images i LEFT JOIN pm.metaData m WHERE p.name LIKE %:value% OR p.description LIKE %:value% OR p.brand LIKE %:value%
			""")

	public List<Product> getProducts(String value);

}
