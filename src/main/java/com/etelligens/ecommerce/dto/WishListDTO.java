package com.etelligens.ecommerce.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class WishListDTO {
    private Long id;
    private String userEmail;
    private Date createdDate;
    private ProductDTO product;

}
