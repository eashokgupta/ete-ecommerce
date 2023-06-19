package com.etelligens.ecommerce.repositories;

import com.etelligens.ecommerce.model.OrderTrack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderTrackRepository extends JpaRepository<OrderTrack,Long> {
}
