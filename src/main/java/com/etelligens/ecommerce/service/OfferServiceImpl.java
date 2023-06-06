package com.etelligens.ecommerce.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.etelligens.ecommerce.dto.OfferDTO;
import com.etelligens.ecommerce.model.Offer;
import com.etelligens.ecommerce.repositories.OfferRepository;

@Component
public class OfferServiceImpl implements OfferService {

	@Autowired
	OfferRepository offerRepository;

	@Autowired
	ModelMapper mapper;

	@Override
	public OfferDTO addOffer(OfferDTO offer) {
		Offer off = mapper.map(offer, Offer.class);
		return mapper.map(offerRepository.save(off), OfferDTO.class);
	}

	@Override
	public List<OfferDTO> getAllOffer() {
		return mapper.map(offerRepository.findAll(), new TypeToken<List<OfferDTO>>() {
		}.getType());
	}

	@Override
	public OfferDTO getOfferById(Long id) {
		try {
			return mapper.map(offerRepository.findById(id).orElseThrow(), OfferDTO.class);
		} catch (NoSuchElementException e) {
			throw new NoSuchElementException("Offer Not Found For Id " + id);
		}

	}

	@Override
	public OfferDTO updateOffer(OfferDTO offer) {
		try {
			Boolean of = offerRepository.findById(offer.getId()).isEmpty();
			if (Boolean.FALSE.equals(of)) {
				Offer off = mapper.map(offer, Offer.class);
				return mapper.map(offerRepository.save(off), OfferDTO.class);
			}
			throw new NoSuchElementException();
		} catch (NoSuchElementException e) {
			throw new NoSuchElementException("Offer Not Found For Id " + offer.getId());
		}
	}

	@Override
	public String deleteOffer(Long id) {
		offerRepository.deleteById(id);
		Boolean off = offerRepository.findById(id).isEmpty();
		if (Boolean.TRUE.equals(off))
			return "Deleted Successfully!";
		return "Not Deleted!";
	}

}
