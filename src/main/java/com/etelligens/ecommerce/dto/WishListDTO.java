package com.etelligens.ecommerce.dto;

import com.etelligens.ecommerce.model.Product;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class WishListDTO {
    private Long id;
    private String userEmail;
//    private Long productId;
    private Date createdDate;
    private ProductDTO product;

}
