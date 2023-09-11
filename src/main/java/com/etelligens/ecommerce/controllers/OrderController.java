package com.etelligens.ecommerce.controllers;

import com.etelligens.ecommerce.auth.service.UserService;
import com.etelligens.ecommerce.dto.OrderDto;
import com.etelligens.ecommerce.model.Orders;
import com.etelligens.ecommerce.service.OrderService;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/*This Controller class will work for Create order,update,get order and delete order.
*
 */
@RestController
@RequestMapping("/ecommerce/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    UserService userService;
    /*
    *  This API will work for Get Order by ID .
     */
    @GetMapping("/{id}")
    public ResponseEntity<Orders> getOrder(@PathVariable("id") Long id) {
       return ResponseEntity.ok(orderService.getOrderById(id));
    }
    /*
     *  This API will work for Create Order for user.
     */
    @PostMapping("/createOrder")
    public ResponseEntity<OrderDto> createOrder(HttpServletRequest request, @RequestBody OrderDto order) {
       String userId = userService.getUserName(request);
       return ResponseEntity.status(HttpStatus.OK).body(orderService.createOrder(userId,order));
    }
    /*
     *  This API will work for Update Order
     */
    @PutMapping("/updateorder")
    public ResponseEntity<OrderDto> updateOrder( @RequestBody OrderDto order) {
       return ResponseEntity.ok(orderService.updateOrder(order));
    }
    /*
     *  This API will work for Delete Order by ID .
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable("id") Long id) {
       return ResponseEntity.ok(orderService.deleteOrder(id));
    }
}