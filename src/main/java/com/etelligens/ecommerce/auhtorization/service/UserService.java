package com.etelligens.ecommerce.auhtorization.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.etelligens.ecommerce.auhtorization.model.User;
import com.etelligens.ecommerce.auhtorization.repositories.UserRepository;
import com.etelligens.ecommerce.model.UserDTO;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	 @Autowired
	 private PasswordEncoder passwordEncoder;
	 
	 @Autowired
	 private ModelMapper mapp;
	
	
	public String addUser(UserDTO userInfo) {
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        User user = mapp.map(userInfo, User.class);
        userRepository.save(user);
        return "user added to system ";
    }
	
	public User getUser(String email) {
		return userRepository.findByEmail(email).get();
	}

}
