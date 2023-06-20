package com.etelligens.ecommerce.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderTrackDto {

    private Long id;

    private String orderId;

    private String status;
}
