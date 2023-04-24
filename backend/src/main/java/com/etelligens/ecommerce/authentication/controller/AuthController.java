package com.etelligens.ecommerce.authentication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import com.etelligens.ecommerce.auhtorization.exception.BadCredentialException;
import com.etelligens.ecommerce.auhtorization.model.AuthenticationRequest;
import com.etelligens.ecommerce.auhtorization.model.User;
import com.etelligens.ecommerce.auhtorization.service.JwtUtil;
import com.etelligens.ecommerce.auhtorization.service.UserService;
import com.etelligens.ecommerce.model.UserDTO;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	JwtUtil jwtUtil;
	
	@Autowired
	UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;
    
    @PostMapping("/registration")
    public String addNewUser(@RequestBody UserDTO userInfo) {
        return userService.addUser(userInfo);
    }
	
    @PostMapping("/login")
    public ResponseEntity<?> authenticateAndGetToken(@RequestBody AuthenticationRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            return new ResponseEntity<>(jwtUtil.generateToken(authRequest.getUsername()), HttpStatus.OK) ;
        } else {
            throw new BadCredentialException("Bad Credential Exception ");
        }
    }
}
