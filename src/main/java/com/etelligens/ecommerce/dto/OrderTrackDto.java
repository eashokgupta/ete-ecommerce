package com.etelligens.ecommerce.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderTrackDto {

    private Long id;

    private String orderId;

    private List<String> orderStatus;
}
