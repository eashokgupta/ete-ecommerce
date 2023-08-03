package com.etelligens.ecommerce.service;

import com.etelligens.ecommerce.auth.model.User;
import com.etelligens.ecommerce.auth.repositories.UserRepository;
import com.etelligens.ecommerce.dto.AddressDTO;
import com.etelligens.ecommerce.dto.OrderDto;
import com.etelligens.ecommerce.dto.ProductDTO;
import com.etelligens.ecommerce.model.Address;
import com.etelligens.ecommerce.model.Orders;
import com.etelligens.ecommerce.model.Payment;
import com.etelligens.ecommerce.model.Product;
import com.etelligens.ecommerce.repositories.AddressRepository;
import com.etelligens.ecommerce.repositories.OrderRepo;

import com.etelligens.ecommerce.repositories.PaymentRepository;
import com.etelligens.ecommerce.repositories.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderServiceImpl implements OrderService {
	
    @Autowired
     UserRepository userRepository;
    
	@Autowired
	private OrderRepo orderRepo;

	@Autowired
	ModelMapper mapper;
	@Autowired
	ProductRepository productRepository;

	@Autowired
	AddressRepository addressRepository;

	@Autowired
	PaymentRepository paymentRepository;

	@Override
	public OrderDto getOrderById(Long id) {
		Orders orders = orderRepo.findById(id).orElse(null);
		if (orders == null) {
			return null;
		}
		Optional<Product> products = productRepository.findById(orders.getId());
		Optional<Address> address = addressRepository.findById(orders.getId());
		OrderDto orderDto = mapper.map(orders, OrderDto.class);
		orderDto.setProductDTOS(Arrays.asList(mapper.map(products, ProductDTO.class)));
		orderDto.setAddress(mapper.map(address, AddressDTO.class));
		return orderDto;
	}


	@Override
	public OrderDto createOrder(String userId, OrderDto order) {
		User user = userRepository.findByEmail(userId).orElseThrow();
		Orders createOrder = mapper.map(order, Orders.class);
		 createOrder.setUser(user);
		return mapper.map(orderRepo.save(createOrder), OrderDto.class);
	
	}

	@Override
	public OrderDto updateOrder( OrderDto order) {
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