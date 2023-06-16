package com.etelligens.ecommerce.service;

import com.etelligens.ecommerce.dto.ProductDTO;
import com.etelligens.ecommerce.dto.WishListDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface WishlistService {

    public String createWishlist(String userEmail,WishListDTO wishList);
    public List<ProductDTO> readWishList(String userEmail);

    String deleteWishList(Long id);

}
