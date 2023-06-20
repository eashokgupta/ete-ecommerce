package com.etelligens.ecommerce.service;

import com.etelligens.ecommerce.dto.OrderTrackDto;
import com.etelligens.ecommerce.model.OrderTrack;
import org.springframework.stereotype.Service;

@Service
public interface OrderTrackService {

    OrderTrack getOrder(Long orderId);

    OrderTrack updateOrderStatus(Long orderId, String status);
}
