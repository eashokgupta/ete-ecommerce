package com.etelligens.ecommerce.dto;

import com.etelligens.ecommerce.auth.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
	    private Long id;
	    private String customerName;
	    private String customerEmail;
	    private List<OrderItemDto> orderItems = new ArrayList<>();
	    private Date orderDate;
	    private UserDTO user;
	    private List<AddressDTO> address;
	    
	   
}
