package com.etelligens.ecommerce.service;

import com.etelligens.ecommerce.dto.OrderTrackDto;
import com.etelligens.ecommerce.model.OrderTrack;
import org.springframework.stereotype.Service;

@Service
public interface OrderTrackService {

    OrderTrackDto getOrder(Long orderId);

    OrderTrackDto updateOrderStatus(Long orderId, String orderStatus);
}
