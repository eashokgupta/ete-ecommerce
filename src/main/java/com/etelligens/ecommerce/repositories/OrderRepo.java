package com.etelligens.ecommerce.repositories;

import com.etelligens.ecommerce.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface OrderRepo extends JpaRepository<Orders, Long> {
	


	
}
