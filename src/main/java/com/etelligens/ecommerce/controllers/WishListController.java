package com.etelligens.ecommerce.controllers;

import com.etelligens.ecommerce.auth.service.UserService;
import com.etelligens.ecommerce.dto.ProductDTO;
import com.etelligens.ecommerce.dto.WishListDTO;
import com.etelligens.ecommerce.service.ProductService;
import com.etelligens.ecommerce.service.WishlistService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
* This Controller class will work for add,update,get and delete  product from whislist.
*/
@RestController
@RequestMapping("/ecommerce")
public class WishListController {

    @Autowired
    private WishlistService wishListService;

    @Autowired
    ProductService productService;

    @Autowired
    UserService userService;

    /**
	* This Api will work for get products from wishlist.
	*/
    @GetMapping("/get-wishlist")
    public ResponseEntity<List<ProductDTO>> getWishList(HttpServletRequest request) {
        String userEmail = userService.getUserName(request);
        return new ResponseEntity<>(wishListService.readWishList(userEmail), HttpStatus.OK);
    }


    /**
	* This Api will work for add products to wishlist.
	*/
    
    @PostMapping("/wishlist")
    public ResponseEntity<String> createWishList(HttpServletRequest request, @RequestBody WishListDTO wishList) {
        String userEmail = userService.getUserName(request);
        return new ResponseEntity<>(wishListService.createWishlist(userEmail,wishList),HttpStatus.OK);

    }
    
    /**
	* This Api will work for delete products to wishlist By id.
	*/
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> removeWishList(@PathVariable Long id){
        return new ResponseEntity<>(wishListService.deleteWishList(id),HttpStatus.OK);
    }
}
