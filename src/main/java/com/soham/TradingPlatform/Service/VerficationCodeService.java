package com.soham.TradingPlatform.Service;

import com.soham.TradingPlatform.Domain.VerificationType;
import com.soham.TradingPlatform.Entity.User;
import com.soham.TradingPlatform.Entity.VerificationCode;

public interface VerficationCodeService {
    VerificationCode sendVerfication(User user, VerificationType verificationType);
    VerificationCode getVerificationCodeById(Long id) throws Exception;
    VerificationCode getVerificationCodeByUser(Long userId);
    void deleteVerificationCodeById(VerificationCode verificationCode);


}
