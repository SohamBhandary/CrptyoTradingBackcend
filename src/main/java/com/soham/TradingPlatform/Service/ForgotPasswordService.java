package com.soham.TradingPlatform.Service;


import com.soham.TradingPlatform.Domain.VerificationType;
import com.soham.TradingPlatform.Entity.ForgotPasswordToken;
import com.soham.TradingPlatform.Entity.User;

public interface ForgotPasswordService {
    ForgotPasswordToken createToken(User user, String id, String otp, VerificationType verificationType,String sendTo);
    ForgotPasswordToken findById(String id);
    ForgotPasswordToken findByUser(Long userId);
    void deleteToken(ForgotPasswordToken token);

}
