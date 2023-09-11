package com.etelligens.ecommerce.controllers;

import com.etelligens.ecommerce.model.Payment;
import com.etelligens.ecommerce.service.PaymentService;
import com.razorpay.RazorpayException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
*This Controller class will work for payment of order
 */
@RestController
@RequestMapping("/toAll")
public class PaymentController {
	@Autowired
	PaymentService paymentService;

	/*
	 * This API will work for create payment of order
	 */
	@PostMapping("/transaction")
	public ResponseEntity<Payment> createTransaction(@RequestBody Payment payment) throws RazorpayException {
		return new ResponseEntity<>(paymentService.createTransaction(payment), HttpStatus.CREATED);
	}
}
