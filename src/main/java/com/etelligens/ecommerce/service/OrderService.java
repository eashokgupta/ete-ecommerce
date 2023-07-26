package com.etelligens.ecommerce.service;

import com.etelligens.ecommerce.dto.OrderDto;
import com.etelligens.ecommerce.model.Orders;


public interface OrderService {

	public Orders getOrderById(long id);

	public OrderDto createOrder(String userId, OrderDto order);

	public OrderDto updateOrder(OrderDto order);


	public String deleteOrder(long id);




}