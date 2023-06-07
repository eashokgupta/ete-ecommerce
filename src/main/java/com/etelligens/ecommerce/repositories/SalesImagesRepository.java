package com.etelligens.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.etelligens.ecommerce.model.SalesImages;

@Repository
public interface SalesImagesRepository extends JpaRepository<SalesImages, Long>{

}
