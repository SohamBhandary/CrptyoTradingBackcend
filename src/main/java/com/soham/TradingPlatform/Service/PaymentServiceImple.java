package com.soham.TradingPlatform.Service;

import com.razorpay.Payment;
import com.razorpay.PaymentLink;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.soham.TradingPlatform.Domain.PaymentMethod;
import com.soham.TradingPlatform.Domain.PaymentOrderStatus;
import com.soham.TradingPlatform.Entity.PaymentOrder;
import com.soham.TradingPlatform.Entity.User;
import com.soham.TradingPlatform.Repository.PaymentOrderRepository;
import com.soham.TradingPlatform.Response.PaymentResponse;
import com.stripe.Stripe;
import com.stripe.param.billingportal.SessionCreateParams;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PaymentServiceImple implements PaymentService{

    @Autowired
    private PaymentOrderRepository paymentOrderRepository;



    @Value(("${razorpay.api.key}"))
    private String apiKey;

    @Value(("${razorpay.api.secretkey}"))
    private String apiSecretKey;


    @Override
    public PaymentOrder createOrder(User user, Long amount, PaymentMethod paymentMethod) {
        PaymentOrder paymentOrder= new PaymentOrder();
        paymentOrder.setUser(user);
        paymentOrder.setAmount(amount);
        paymentOrder.setMethod(paymentMethod);
        return null;
    }

    @Override
    public PaymentOrder getPaymentOrderById(Long id) throws Exception {
        Optional<PaymentOrder> optionalPaymentOrder=paymentOrderRepository.findById(id);
        if(optionalPaymentOrder.isEmpty()){
            throw new Exception("payment order not found with id "+id);
        }
        return optionalPaymentOrder.get();
    }

    @Override
    public Boolean proccedPaymentOrder(PaymentOrder paymentOrder, String paymentId) throws RazorpayException {
        if(paymentOrder.getStatus().equals(PaymentOrderStatus.PENDING)){
            if(paymentOrder.getMethod().equals(PaymentMethod.RAZORPAY)){
                RazorpayClient razorpayClient=new RazorpayClient(apiKey,apiSecretKey);
                Payment payment=razorpayClient.payments.fetch(paymentId);

                Integer amount=payment.get("amount");
                String status=payment.get("status");

                if(status.equals("captured")){
                    paymentOrder.setStatus(PaymentOrderStatus.SUCCESS);
                    return true;
                }
                paymentOrder.setStatus(PaymentOrderStatus.FAIL);
                paymentOrderRepository.save(paymentOrder);
                return false;



            }
        }
        return null;
    }





    @Override
    public PaymentResponse createRazorpayPaymentLink(User user, Long amount, Long id) throws RazorpayException {
        Long amount1=amount*100;
        try {

            RazorpayClient razorpay = new RazorpayClient(apiKey, apiSecretKey);

            JSONObject paymentLinkRequest = new JSONObject();
            paymentLinkRequest.put("amount",amount);
            paymentLinkRequest.put("currency","INR");

            JSONObject customer = new JSONObject();
            customer.put("name",user.getFullName());

            customer.put("email",user.getEmail());
            paymentLinkRequest.put("customer",customer);
            JSONObject notify = new JSONObject();
            notify.put("email",true);
            paymentLinkRequest.put("notify",notify);

            paymentLinkRequest.put("reminder_enable",true);

            // Set the callback URL and method
            paymentLinkRequest.put("callback_url","http://localhost:5173/wallet/");
            paymentLinkRequest.put("callback_method","get");

            // Create the payment link using the paymentLink.create() method
            PaymentLink payment = razorpay.paymentLink.create(paymentLinkRequest);

            String paymentLinkId = payment.get("id");
            String paymentLinkUrl = payment.get("short_url");

            PaymentResponse res=new PaymentResponse();
            res.setPayment_url(paymentLinkUrl);


            return res;

        } catch (RazorpayException e) {

            System.out.println("Error creating payment link: " + e.getMessage());
            throw new RazorpayException(e.getMessage());
        }
    }


}
