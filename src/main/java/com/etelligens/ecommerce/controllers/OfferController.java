package com.etelligens.ecommerce.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.etelligens.ecommerce.dto.OfferDTO;
import com.etelligens.ecommerce.dto.SalesDTO;
import com.etelligens.ecommerce.service.OfferService;
import com.etelligens.ecommerce.service.SalesService;


/**
* This Controller class will work for add ,get,update and delete Offers and sales.
*/
@RestController
@RequestMapping("/toAll")
public class OfferController {
	
	@Autowired
	OfferService offerService;
	
	@Autowired
	SalesService salesService;
	

/**
* This Api will work for add offer in products.
*/
	@PostMapping("/newOffer")
	public ResponseEntity<OfferDTO> addOffers(@RequestBody OfferDTO offer){
	     return new ResponseEntity<>(offerService.addOffer(offer), HttpStatus.OK);
	}
	
	/**
	* This Api will work for add offer in products.
	*/
	@GetMapping("/getOffers")
	public ResponseEntity<List<OfferDTO>> getOffers(){
		return new ResponseEntity<>(offerService.getAllOffer(), HttpStatus.OK);
	}
	/**
	* This Api will work for update offer in products.
	*/
	@PutMapping("/updateOffer")
	public ResponseEntity<OfferDTO> updateOffer(@RequestParam("offer") OfferDTO offer){
		return new ResponseEntity<>(offerService.updateOffer(offer), HttpStatus.OK);
	}
	
	/**
	* This Api will work for delete offer in products by id.
	*/
	
	@DeleteMapping("/deleteOffer/{id}")
	public ResponseEntity<String> deleteOffer(@PathVariable("id") Long id){
		return new ResponseEntity<>(offerService.deleteOffer(id), HttpStatus.OK);
	}
	
	/**
	* This Api will work for add sale in products.
	*/
	@PostMapping("newSales")
	public ResponseEntity<SalesDTO> addNewSales(@RequestParam("sales") String sales, @RequestParam("img") MultipartFile[] salesImgs){
		return new ResponseEntity<>(salesService.addNewSales(sales, salesImgs), HttpStatus.OK);
	}
	
	/**
	* This Api will work for get sale in products By title.
	*/
	@GetMapping("/getSales/{title}")
	public ResponseEntity<SalesDTO> getSales(@PathVariable("title") String title){
		return new ResponseEntity<>(salesService.getSalesByTitle(title), HttpStatus.OK);
	}
	/**
	* This Api will work for update sale in products.
	*/
	@PutMapping("/updateSales")
	public ResponseEntity<SalesDTO> updateSales(@RequestParam("sales") String sales, @RequestParam("img") MultipartFile[] salesImgs){
		return new ResponseEntity<>(salesService.updateSales(sales, salesImgs), HttpStatus.OK);
	}
	/**
	* This Api will work for delete sale in products by id.
	*/
	@DeleteMapping("/deleteSales/{id}")
	public ResponseEntity<String> deleteSales(@PathVariable("id") Long id){
		return new ResponseEntity<>(salesService.deleteSales(id), HttpStatus.OK);
	}
}
