package com.etelligens.ecommerce.auhtorization.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.etelligens.ecommerce.auhtorization.exception.LoginException;
import com.etelligens.ecommerce.auhtorization.model.AuthenticationRequest;
import com.etelligens.ecommerce.auhtorization.model.User;
import com.etelligens.ecommerce.auhtorization.repositories.UserRepository;
import com.etelligens.ecommerce.model.UserDTO;
import com.etelligens.ecommerce.model.UserResponseDTO;

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

	public UserResponseDTO addUser(UserDTO userInfo) throws LoginException {
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
		} catch (BadCredentialsException e) {
			throw new LoginException(userInfo.getEmail() + " is already existing!");
		}

	}

	public User getUser(String email) {
		return userRepository.findByEmail(email).orElseThrow();
	}
	
	public UserResponseDTO login(AuthenticationRequest authRequest) {
		try {
		authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));

		} catch (BadCredentialsException e) {
			throw new LoginException("Bad Credential Exception");
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

}
