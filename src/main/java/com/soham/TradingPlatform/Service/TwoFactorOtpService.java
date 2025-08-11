package com.soham.TradingPlatform.Service;

import com.soham.TradingPlatform.Entity.TwoFactorOTP;
import com.soham.TradingPlatform.Entity.User;


public interface TwoFactorOtpService {
    TwoFactorOTP createTwoFactorOtp(User user, String otp, String jwt);

    TwoFactorOTP findByUser(Long userId);

    TwoFactorOTP findById(String id);

    boolean verifyTwoFactorOtp(TwoFactorOTP twoFactorOTP,String otp);

    void deleteTwoFactorOtp(TwoFactorOTP twoFactorOTP);



}
