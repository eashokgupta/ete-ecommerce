package com.etelligens.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.etelligens.ecommerce.model.ProductMetaData;

@Repository
public interface ProductMetaDataRepository extends JpaRepository<ProductMetaData, Long>{

}
