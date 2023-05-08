package com.etelligens.ecommerce.service;

import java.io.IOException;
import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.etelligens.ecommerce.dto.ProfileDTO;
import com.etelligens.ecommerce.model.Profile;


@Service
@Component
public interface ProfileService {
	public ProfileDTO addProfile(MultipartFile file ,ProfileDTO s) throws IOException;

	public String deleteProfileById(int id);

	public ProfileDTO updateProfile(MultipartFile file, ProfileDTO s) throws IOException;

	public Optional<Profile> getProfileById(int id);

}
