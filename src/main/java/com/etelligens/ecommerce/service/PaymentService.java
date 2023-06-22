package com.etelligens.ecommerce.service;

import com.etelligens.ecommerce.model.Payment;
import com.razorpay.RazorpayException;
import org.springframework.stereotype.Service;

@Service
public interface PaymentService {
    Payment createTransaction(Payment payment) throws RazorpayException;

}
