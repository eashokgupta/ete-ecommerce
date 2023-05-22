package com.etelligens.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.etelligens.ecommerce.model.Images;

public interface ProductImagesRepo extends JpaRepository<Images, Long>{

}
