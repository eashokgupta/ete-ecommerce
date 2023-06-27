package com.etelligens.ecommerce.service;

import com.etelligens.ecommerce.dto.OrderDto;
import com.etelligens.ecommerce.model.Orders;

import java.util.Optional;

public interface OrderService {

	public Optional<Orders> getOrderById(long id);

	public OrderDto createOrder( OrderDto order);

	public OrderDto updateOrder(OrderDto order);


	public String deleteOrder(long id);




}