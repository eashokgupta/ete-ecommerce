package com.etelligens.ecommerce.service;

import com.etelligens.ecommerce.dto.OrderDto;
import com.etelligens.ecommerce.model.Orders;


public interface OrderService {

	public OrderDto getOrderById(Long id);

	public OrderDto createOrder(String userId, OrderDto order);

	public OrderDto updateOrder(OrderDto order);


	public String deleteOrder(long id);




}