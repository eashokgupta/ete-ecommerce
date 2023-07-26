package com.etelligens.ecommerce.auth.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.etelligens.ecommerce.auth.dto.UserDTO;
import com.etelligens.ecommerce.auth.dto.UserResponseDTO;
import com.etelligens.ecommerce.auth.exception.LoginException;
import com.etelligens.ecommerce.auth.model.AuthenticationRequest;
import com.etelligens.ecommerce.auth.model.User;
import com.etelligens.ecommerce.auth.repositories.UserRepository;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private ModelMapper mapp;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtUtil;

	public UserResponseDTO addUser(UserDTO userInfo)  {
		String authPassword = userInfo.getPassword();
		userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
		User user = mapp.map(userInfo, User.class);
		userRepository.findByEmail(userInfo.getEmail()).ifPresent(userExist -> {
			throw new LoginException(userExist.getEmail() + " emailId is already existing!");
		});
		try {
			User user1 = userRepository.save(user);
			authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(userInfo.getEmail(), authPassword));

			String token = jwtUtil.generateToken(userInfo.getEmail());
			UserResponseDTO uResponseDTO = new UserResponseDTO();
			uResponseDTO.setToken(token);
			uResponseDTO.setEmailId(user1.getEmail());
			uResponseDTO.setUsername(user1.getName());
			uResponseDTO.setContactNumber(user1.getContactNumber());
			return uResponseDTO;
		} catch (Exception e) {
			throw new LoginException(userInfo.getEmail() + " is already existing!");
		}

	}

	public User getUser(String email) {
		return userRepository.findByEmail(email).orElseThrow();
	}
	
	public UserResponseDTO login(AuthenticationRequest authRequest) {
		Optional<User> user = userRepository.findByEmail(authRequest.getUsername());
		if(user.isEmpty()) {
			throw new LoginException("User Not Found!");
		}
		try {
			
		authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));

		} catch (Exception e) {
			throw new LoginException("Password Incorrect!");
		}
		String token = jwtUtil.generateToken(authRequest.getUsername());
		UserResponseDTO uResponseDTO = new UserResponseDTO();
		uResponseDTO.setToken(token);
		uResponseDTO.setEmailId(authRequest.getUsername());
		User userDetail = this.getUser(authRequest.getUsername());
		uResponseDTO.setUsername(userDetail.getName());
		uResponseDTO.setContactNumber(userDetail.getContactNumber());
		return uResponseDTO;
		
	}

	public String getUserName(HttpServletRequest request) {
		String authHeader = request.getHeader("Authorization");
	     String token = null;
	     String userId = null;
	     if (authHeader != null && authHeader.startsWith("Bearer ")) {
	         token = authHeader.substring(7);
	      return   userId = jwtUtil.extractUsername(token);
	     }
		return userId;
		
	}
}
