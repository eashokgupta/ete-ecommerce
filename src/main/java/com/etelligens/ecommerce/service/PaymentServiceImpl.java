package com.etelligens.ecommerce.service;

import com.etelligens.ecommerce.model.Payment;
import com.etelligens.ecommerce.repositories.PaymentRepository;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService{

    public static final String KEY ="rzp_test_IYZKpb1AuS0G1J";

    public static final String KEY_SECRET="5IsnIFiHrRwuNzu2RuAPMwqC";

    @Autowired
    PaymentRepository paymentRepository;


    @Override
    public Payment createTransaction(Payment payment) throws RazorpayException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("amount",(payment.getAmount()*100));
        jsonObject.put("currency",payment.getCurrency());
        jsonObject.put("receipt",payment.getReceipt());

        RazorpayClient razorpayClient = new RazorpayClient(KEY,KEY_SECRET);
        Order order = razorpayClient.orders.create(jsonObject);
        return paymentRepository.save(prepareOrder(order));
    }
    private Payment prepareOrder(Order order) {
        String orderId = order.get("id");
        String currency = order.get("currency");
        Integer amount = order.get("amount");
        String receipt = order.get("receipt");
        return new Payment(orderId,currency,amount,receipt);

    }
}
