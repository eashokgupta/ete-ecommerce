package com.etelligens.ecommerce.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.etelligens.ecommerce.dto.OfferDTO;

@Component
public interface OfferService {
	public OfferDTO addOffer(OfferDTO offer);

	public List<OfferDTO> getAllOffer();

	public OfferDTO getOfferById(Long id);

	public OfferDTO updateOffer(OfferDTO offer);

	public String deleteOffer(Long id);

}
