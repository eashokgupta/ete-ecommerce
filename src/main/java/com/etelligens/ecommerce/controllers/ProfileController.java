package com.etelligens.ecommerce.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.etelligens.ecommerce.auth.service.UserService;
import com.etelligens.ecommerce.dto.ProfileDTO;
import com.etelligens.ecommerce.service.ProfileService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/ecommerce/orders")
public class ProfileController {

	@Autowired
	ProfileService profileService;

	@Autowired
	UserService userService;

	@PostMapping("/createProfile")
	public ResponseEntity<ProfileDTO> createProfile(HttpServletRequest request,
			@RequestParam("file") MultipartFile file, @RequestParam("detail") String profile) throws IOException {
		String userId = userService.getUserName(request);
		return ResponseEntity.status(HttpStatus.OK).body(profileService.addProfile(userId, file, profile));

	}

	@GetMapping("/profile")
	public ResponseEntity<ProfileDTO> findById(HttpServletRequest request) {
		String userId = userService.getUserName(request);
		return new ResponseEntity<>(profileService.getProfileById(userId), HttpStatus.OK);
	}

	@DeleteMapping("/deleteProfile/{id}")
	public ResponseEntity<String> deleteProfile(@PathVariable Long id) {
		return new ResponseEntity<>(profileService.deleteProfileById(id), HttpStatus.OK);
	}

	@PutMapping("updateProfile")
	public ResponseEntity<ProfileDTO> updateProfile(@RequestParam("file") MultipartFile file,
			@RequestParam("detail") String profile) throws IOException {
		return ResponseEntity.status(HttpStatus.OK).body(profileService.updateProfile(file, profile));
	}

}
