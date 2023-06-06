package com.etelligens.ecommerce.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.etelligens.ecommerce.dto.OfferDTO;
import com.etelligens.ecommerce.service.OfferService;

@RestController
@RequestMapping("/toAll")
public class OfferController {
	
	@Autowired
	OfferService offerService;
	
	@PostMapping("/newOffer")
	public ResponseEntity<OfferDTO> addOffers(@RequestBody OfferDTO offer){
	     OfferDTO off =	offerService.addOffer(offer);
	     return new ResponseEntity<>(off, HttpStatus.OK);
	}
	
	@GetMapping("/getOffers")
	public ResponseEntity<List<OfferDTO>> getOffers(){
		return new ResponseEntity<>(offerService.getAllOffer(), HttpStatus.OK);
	}

}
