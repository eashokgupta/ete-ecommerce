package com.etelligens.ecommerce.service;

import com.etelligens.ecommerce.dto.OrderTrackDto;
import com.etelligens.ecommerce.model.OrderTrack;
import com.etelligens.ecommerce.repositories.OrderTrackRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class OrderTrackServiceImpl implements OrderTrackService{

    @Autowired
    OrderTrackRepository trackRepository;
    @Autowired
    ModelMapper mapper;

    @Override
    public OrderTrackDto getOrder(Long orderId) {
       return mapper.map(trackRepository.findById(orderId)
               .orElseThrow(() -> new IllegalArgumentException("Order not found with orderId: " + orderId)),OrderTrackDto.class);

    }


    @Override
    public OrderTrackDto updateOrderStatus(Long orderId, String orderStatus) {
        OrderTrack order = trackRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found with id: " + orderId));
        order.setOrderStatus(Arrays.asList(orderStatus));
        return mapper.map(trackRepository.save(order),OrderTrackDto.class);
    }
}
