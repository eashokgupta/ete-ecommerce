package com.etelligens.ecommerce.service;

import java.io.IOException;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.etelligens.ecommerce.auth.model.User;
import com.etelligens.ecommerce.auth.repositories.UserRepository;
import com.etelligens.ecommerce.dto.ProfileDTO;
import com.etelligens.ecommerce.exception.ResourceNotFoundException;
import com.etelligens.ecommerce.model.Profile;
import com.etelligens.ecommerce.repositories.ProfileRepository;
import com.etelligens.ecommerce.utils.StringToObjectConvert;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@Service
@Component
public class ProfileServiceImpl implements ProfileService {

	@Autowired
	ProfileRepository profileRepo;

	@Autowired
	UserRepository userRepository;

	@Autowired
	ModelMapper mapper;

	@Autowired
	StringToObjectConvert stringToObjectConvert;

	@Override
	public ProfileDTO addProfile(String userId, MultipartFile file, String profile) throws IOException {
		ProfileDTO profileDTO = stringToObjectConvert.convertJsonToObject(profile, ProfileDTO.class);
		profileDTO.setProfilePhoto(file.getBytes());
		User user = userRepository.findByEmail(userId).orElseThrow();
		Profile savedProfile = mapper.map(profileDTO, Profile.class);
		savedProfile.setUser(user);
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
	public ProfileDTO updateProfile(MultipartFile file, String profile) throws IOException {
		ProfileDTO profileDTO = stringToObjectConvert.convertJsonToObject(profile, ProfileDTO.class);
		Optional<Profile> existingProfile = profileRepo.findById(profileDTO.getId());
		try {
			if (!existingProfile.isEmpty()) {
				profileDTO.setProfilePhoto(file.getBytes());
				Profile updateProfile = mapper.map(profileDTO, Profile.class);
				updateProfile.setUser(existingProfile.get().getUser());
				updateProfile = profileRepo.save(updateProfile);
				return mapper.map(updateProfile, ProfileDTO.class);
			}
			return null;
		} catch (ResourceNotFoundException e) {
			throw new ResourceNotFoundException("Profile Not Found" + profileDTO.getId());

		}
	}

	@Override
	public ProfileDTO getProfileById(String userId) {
		Optional<Profile> profile = profileRepo.findByUserEmail(userId);
		return mapper.map(profile.orElseThrow(), ProfileDTO.class);
	}

}
