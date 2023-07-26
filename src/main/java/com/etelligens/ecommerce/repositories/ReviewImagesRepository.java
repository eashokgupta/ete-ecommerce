package com.etelligens.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.etelligens.ecommerce.model.ReviewImages;

@Repository
public interface ReviewImagesRepository extends JpaRepository<ReviewImages, Long>{

}
