package com.soham.TradingPlatform.Service;

import com.razorpay.RazorpayException;
import com.soham.TradingPlatform.Domain.PaymentMethod;
import com.soham.TradingPlatform.Entity.PaymentOrder;
import com.soham.TradingPlatform.Entity.User;
import com.soham.TradingPlatform.Response.PaymentResponse;

public interface PaymentService {

    PaymentOrder createOrder(User user, Long amount, PaymentMethod paymentMethod);
    PaymentMethod getPaymentOrderById(Long id) throws Exception;

    Boolean proccedPaymentOrder(PaymentOrder paymentOrder,String paymentId) throws RazorpayException;

    PaymentResponse createRayzorPayPAymentLink(User user, Long amount);

    PaymentResponse createStripePayPAymentLink(User user, Long amount,Long orderId);
}
