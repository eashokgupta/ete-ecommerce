package com.etelligens.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.etelligens.ecommerce.model.Offer;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {

}
