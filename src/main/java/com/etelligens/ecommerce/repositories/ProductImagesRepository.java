package com.etelligens.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.etelligens.ecommerce.model.Images;

@Repository
public interface ProductImagesRepository extends JpaRepository<Images, Long> {

}
