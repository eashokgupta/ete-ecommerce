package com.etelligens.ecommerce.service;

import java.io.IOException;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.etelligens.ecommerce.dto.ProfileDTO;
import com.etelligens.ecommerce.exception.ResourceNotFoundException;
import com.etelligens.ecommerce.model.Profile;
import com.etelligens.ecommerce.repositories.ProfileRepository;


@Service
@Component
public class ProfileServiceImpl implements ProfileService {

	@Autowired
	ProfileRepository profileRepo;

	@Autowired
	ModelMapper mapper;

	@Override
	public ProfileDTO addProfile(MultipartFile file, ProfileDTO profile) throws IOException {
		profile.setProfilePhoto(file.getBytes());
		Profile savedProfile = mapper.map(profile, Profile.class);
		return mapper.map(profileRepo.save(savedProfile), ProfileDTO.class);
	}

	@Override
	public String deleteProfileById(Long id) {
		profileRepo.deleteById(id);
		Boolean flag = profileRepo.findById(id).isEmpty();
		if (Boolean.TRUE.equals(flag)) {
			return "Profile Successfully Deleted";
		}
		return "Not Deleted";
	}

	@Override
	public ProfileDTO updateProfile(MultipartFile file, ProfileDTO profile) throws IOException {
		Optional<Profile> existingProfile = profileRepo.findById(profile.getProfileId());
		try {
			if (!existingProfile.isEmpty()) {
				profile.setProfilePhoto(file.getBytes());
				Profile updateProfile = mapper.map(profile, Profile.class);
				updateProfile = profileRepo.save(updateProfile);
				return mapper.map(updateProfile, ProfileDTO.class);
			}
			return null;
		} catch (ResourceNotFoundException e) {
			throw new ResourceNotFoundException("Profile Not Found" + profile.getProfileId());

		}
	}

	@Override
	public Optional<Profile> getProfileById(Long id) {

		return profileRepo.findById(id);
	}

}

