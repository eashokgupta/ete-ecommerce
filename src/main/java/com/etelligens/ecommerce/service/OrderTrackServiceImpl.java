package com.etelligens.ecommerce.service;

import com.etelligens.ecommerce.dto.OrderTrackDto;
import com.etelligens.ecommerce.model.OrderTrack;
import com.etelligens.ecommerce.repositories.OrderTrackRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderTrackServiceImpl implements OrderTrackService{

    @Autowired
    OrderTrackRepository trackRepository;
    @Autowired
    ModelMapper mapper;

    @Override
    public OrderTrack getOrder(Long orderId) {
       return trackRepository.findById(orderId)
               .orElseThrow(() -> new IllegalArgumentException("Order not found with orderId: " + orderId));

    }


    @Override
    public OrderTrack updateOrderStatus(Long orderId, String status) {
        OrderTrack order = trackRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found with id: " + orderId));
        order.setStatus(status);
        return trackRepository.save(order);
    }
}
