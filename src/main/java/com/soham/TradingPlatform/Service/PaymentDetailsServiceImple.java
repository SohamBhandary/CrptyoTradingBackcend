package com.soham.TradingPlatform.Service;

import com.soham.TradingPlatform.Entity.PaymentDetails;
import com.soham.TradingPlatform.Entity.User;
import com.soham.TradingPlatform.Repository.PaymentDetailsReposiotry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentDetailsServiceImple implements  PaymentDetailsService{

    @Autowired
    private PaymentDetailsReposiotry paymentDetailsReposiotry;

    @Override
    public PaymentDetails addPaymentDetails(String accountNumber, String accoutHolderName, String ifsc, String bankName, User user) {
        PaymentDetails paymentDetails= new PaymentDetails();
        paymentDetails.setAccountNumber(accountNumber);
        paymentDetails.setAccountHolderName(accoutHolderName);
        paymentDetails.setIfsc(ifsc);
        paymentDetails.setBankName(bankName);
        paymentDetails.setUser(user);
        return paymentDetailsReposiotry.save(paymentDetails);
    }

    @Override
    public PaymentDetails getUsersPaymentDetails(User user) {
        return paymentDetailsReposiotry.findByUserId(user.getId());
    }
}
