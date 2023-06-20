package com.etelligens.ecommerce.controllers;

import com.etelligens.ecommerce.model.OrderTrack;
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

@RestController
@RequestMapping("/toAll")
public class OrderTrackController {
    @Autowired
    OrderTrackService orderTrackService;

    @GetMapping("/order/{orderId}")
    public ResponseEntity<OrderTrack> findOrder(@PathVariable Long orderId){
        return new ResponseEntity<>(orderTrackService.getOrder(orderId), HttpStatus.OK);
    }

    @PutMapping("/update-track/{orderId}")
    public ResponseEntity<OrderTrack> updateOrderStatus(@PathVariable Long orderId, @RequestBody String status){
        return new ResponseEntity<>(orderTrackService.updateOrderStatus(orderId,status),HttpStatus.CREATED);
    }


}
