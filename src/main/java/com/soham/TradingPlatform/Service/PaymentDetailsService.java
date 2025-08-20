package com.soham.TradingPlatform.Service;

import com.soham.TradingPlatform.Entity.PaymentDetails;
import com.soham.TradingPlatform.Entity.User;



public interface PaymentDetailsService {

    PaymentDetails addPaymentDetails(String accountNumber, String accoutHolderName, String ifsc, String bankName, User user);
    PaymentDetails getUsersPaymentDetails(User user);
}
