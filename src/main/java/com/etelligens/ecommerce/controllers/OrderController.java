package com.etelligens.ecommerce.controllers;

import com.etelligens.ecommerce.dto.OrderDto;
import com.etelligens.ecommerce.model.Orders;
import com.etelligens.ecommerce.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/orders")

public class OrderController {

	@Autowired
	private OrderService orderService;

	@GetMapping("/{id}")
	public ResponseEntity<Orders> getOrder(@PathVariable("id") Long id) {
		Optional<Orders> order = orderService.getOrderById(id);
		return ResponseEntity.ok(order.get());
	}

	@PostMapping("/createOrder")
	public ResponseEntity<OrderDto> createOrder(@RequestBody OrderDto order) {
		OrderDto savedOrder = orderService.createOrder(order);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedOrder);
	}

	@PutMapping("/updateorder")
	public ResponseEntity<OrderDto> updateOrder( @RequestBody OrderDto order) {
		OrderDto updatedOrder = orderService.updateOrder(order);
		return ResponseEntity.ok(updatedOrder);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteOrder(@PathVariable("id") Long id) {
		String result = orderService.deleteOrder(id);
		return ResponseEntity.ok(result);
		
	}

}
