package com.etelligens.ecommerce.auhtorization.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.etelligens.ecommerce.auhtorization.model.User;
import com.etelligens.ecommerce.auhtorization.repositories.UserRepository;

@Component
@Service
public class AuthUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		 Optional<User> user = userRepository.findByEmail(username);
		return user.map(AuthUserDetails::new)
        .orElseThrow(() -> new UsernameNotFoundException("Bad Credential Exception ")); 
	}

}