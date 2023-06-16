package com.etelligens.ecommerce.controllers;

import com.etelligens.ecommerce.auth.model.AuthenticationRequest;
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

import javax.security.auth.login.LoginException;
import java.util.List;

@RestController
@RequestMapping("/ecommerce")
public class WishListController {

    @Autowired
    private WishlistService wishListService;

    @Autowired
    ProductService productService;

    @Autowired
    private AuthenticationRequest authenticationService;

    @Autowired
    UserService userService;

    @GetMapping("/get-wishlist")
    public ResponseEntity<List<ProductDTO>> getWishList(HttpServletRequest request) {
        String userEmail = userService.getUserName(request);
        return new ResponseEntity<>(wishListService.readWishList(userEmail), HttpStatus.OK);
    }

    @PostMapping("/wishlist")
    public ResponseEntity<String> createWishList(HttpServletRequest request, @RequestBody WishListDTO wishList) throws LoginException {
        String userEmail = userService.getUserName(request);
        return new ResponseEntity<>(wishListService.createWishlist(userEmail,wishList),HttpStatus.CREATED);

    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> removeWishList(@PathVariable Long id){
        return new ResponseEntity<>(wishListService.deleteWishList(id),HttpStatus.OK);
    }
}
