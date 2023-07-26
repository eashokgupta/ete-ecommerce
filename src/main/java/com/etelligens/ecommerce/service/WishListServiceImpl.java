package com.etelligens.ecommerce.service;

import com.etelligens.ecommerce.auth.model.User;
import com.etelligens.ecommerce.auth.repositories.UserRepository;
import com.etelligens.ecommerce.dto.ProductDTO;
import com.etelligens.ecommerce.dto.WishListDTO;
import com.etelligens.ecommerce.model.WishList;
import com.etelligens.ecommerce.repositories.ProductRepository;
import com.etelligens.ecommerce.repositories.WishListRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishListServiceImpl implements WishlistService{
    @Autowired
    UserRepository userRepository;

    @Autowired
    WishListRepository wishListRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ModelMapper mapper;
    @Override
    public String createWishlist(String userEmail, WishListDTO wishList) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        if (user.getEmail().equalsIgnoreCase(wishList.getUserEmail())) {
            WishList wishList1 = mapper.map(wishList, WishList.class);
            wishList.setUserEmail(user.getEmail());
            wishListRepository.save(wishList1);
            return "Product successfully added to the wishlist!";
        } else {
            return "Please login with the registered email ID.";
        }
    }

    @Override
    public List<ProductDTO> readWishList(String userEmail) {
       List<WishList> wishLists= wishListRepository.findAllByUserEmailOrderByCreatedDateDesc(userEmail);
       
        return  mapper.map(wishLists,new TypeToken<List<WishListDTO>>() {
		}.getType());
    }

    @Override
    public String deleteWishList(Long id) {
        wishListRepository.deleteById(id);
        return "Product successfully deleted ";
    }


}
