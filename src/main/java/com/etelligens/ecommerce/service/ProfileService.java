package com.etelligens.ecommerce.service;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.etelligens.ecommerce.dto.ProfileDTO;


@Service
@Component
public interface ProfileService {
	public ProfileDTO addProfile(String userId, MultipartFile file ,String profile) throws IOException;

	public String deleteProfileById(Long id);

	public ProfileDTO updateProfile(MultipartFile file, String profile) throws IOException;

	public ProfileDTO getProfileById(String userId);

}
