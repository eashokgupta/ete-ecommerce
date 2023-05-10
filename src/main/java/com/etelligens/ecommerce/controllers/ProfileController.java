package com.etelligens.ecommerce.controllers;

import java.io.IOException;
import java.util.Optional;

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

import com.etelligens.ecommerce.dto.ProfileDTO;
import com.etelligens.ecommerce.model.Profile;
import com.etelligens.ecommerce.service.ProfileService;
import com.google.gson.Gson;



@RestController
@RequestMapping("/profile")
public class ProfileController {

	@Autowired
	ProfileService profileService;

	@PostMapping("/createProfile")
	public ResponseEntity<ProfileDTO> createProfile(@RequestParam("file") MultipartFile file,
			@RequestParam("detail") String profile) throws IOException {
		Gson g = new Gson(); 
		ProfileDTO s = g.fromJson(profile, ProfileDTO.class);

		return ResponseEntity.status(HttpStatus.CREATED).body(profileService.addProfile(file, s));

	}

	@GetMapping("/profile/{id}")
	public Optional<Profile> findById(@PathVariable int id) {
		return profileService.getProfileById(id);
	}

	@DeleteMapping("/deleteProfile/{id}")
	public ResponseEntity<String> deleteProfile(@PathVariable int id) {
		profileService.deleteProfileById(id);

		return new ResponseEntity<>("Profile Successfully Deleted", HttpStatus.OK);
	}

	@PutMapping("updateProfile")
	public ResponseEntity<ProfileDTO> updateProfile(@RequestParam("file") MultipartFile file, @RequestParam("detail") String profile) throws IOException{
		Gson g = new Gson(); 
		ProfileDTO s =g.fromJson(profile, ProfileDTO.class);
				

		return ResponseEntity.status(HttpStatus.OK).body(profileService.updateProfile(file,s));
	}

}
