package com.etelligens.ecommerce.controllers;

import com.etelligens.ecommerce.dto.OrderTrackDto;
import com.etelligens.ecommerce.service.OrderTrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
*    This Controller class will work for track the order
 */
@RestController
@RequestMapping("/ecommerce")
public class OrderTrackController {
    @Autowired
    OrderTrackService orderTrackService;
    /*
     *    This API will work for track the order by ID
     */
    @GetMapping("/order/{orderId}")
    public ResponseEntity<OrderTrackDto> findOrder(@PathVariable Long orderId){
        return new ResponseEntity<>(orderTrackService.getOrder(orderId), HttpStatus.OK);
    }
    /*
     *    This API will work for updated Order's Status by orderID
     */
    @PutMapping("/update-track/{orderId}")
    public ResponseEntity<OrderTrackDto> updateOrderStatus(@PathVariable Long orderId, @RequestBody String status){
        return new ResponseEntity<>(orderTrackService.updateOrderStatus(orderId,status),HttpStatus.OK);
    }
} 
