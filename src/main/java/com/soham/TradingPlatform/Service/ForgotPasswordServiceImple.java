package com.soham.TradingPlatform.Service;

import com.soham.TradingPlatform.Domain.VerificationType;
import com.soham.TradingPlatform.Entity.ForgotPasswordToken;
import com.soham.TradingPlatform.Entity.User;
import com.soham.TradingPlatform.Repository.ForgotPasswordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ForgotPasswordServiceImple implements  ForgotPasswordService{
    @Autowired
    private ForgotPasswordRepository forgotPasswordRepository;
    @Override
    public ForgotPasswordToken createToken(User user, String id, String otp, VerificationType verificationType, String sendTo) {
        ForgotPasswordToken token= new ForgotPasswordToken();
        token.setUser(user);
        token.setSendTo(sendTo);
        token.setOtp(otp);
        token.setId(id);
        return forgotPasswordRepository.save(token);
    }

    @Override
    public ForgotPasswordToken findById(String id) {
        Optional<ForgotPasswordToken> token=forgotPasswordRepository.findById(Long.valueOf(id));
        return token.orElse(null);


    }

    @Override
    public ForgotPasswordToken findByUser(Long userId) {
        return forgotPasswordRepository.findByUserId(Long.valueOf(String.valueOf(userId)));
    }

    @Override
    public void deleteToken(ForgotPasswordToken token) {
        forgotPasswordRepository.delete(token);

    }



}
