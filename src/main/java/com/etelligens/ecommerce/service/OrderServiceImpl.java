package com.etelligens.ecommerce.service;

import com.etelligens.ecommerce.auth.model.User;
import com.etelligens.ecommerce.auth.repositories.UserRepository;
import com.etelligens.ecommerce.dto.OrderDto;
import com.etelligens.ecommerce.model.Orders;
import com.etelligens.ecommerce.repositories.OrderRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
	
    @Autowired
     UserRepository userRepository;
    
	@Autowired
	private OrderRepo orderRepo;

	@Autowired
	ModelMapper mapper;

	@Override
	public Optional<Orders> getOrderById(long id) {
		return orderRepo.findById(id);
	}

	@Override
	public OrderDto createOrder(OrderDto order) {
		User user = userRepository.findByEmail(order.getCustomerEmail()).get();
		Orders createOrder = mapper.map(order, Orders.class);
		 createOrder.setUser(user);
		return mapper.map(orderRepo.save(createOrder), OrderDto.class);
//		.orElseThrow(() -> new EmailNotFoundException("address not found ")); 
	
	}

	@Override
	public OrderDto updateOrder( OrderDto order) {
		Boolean Orders = orderRepo.findById(order.getId()).isEmpty();
		Orders updateOrder = mapper.map(order, Orders.class);
		updateOrder = orderRepo.save(updateOrder);
		return mapper.map(updateOrder, OrderDto.class);
	}

	@Override
	public String deleteOrder(long id) {
		orderRepo.deleteById(id);
		Boolean flag = orderRepo.findById(id).isEmpty();
		if (Boolean.TRUE.equals(flag)) {
			return "deleted succesfully";
		}
		return "not deleted";
	}

	
}