package com.soham.TradingPlatform.Service;

import com.soham.TradingPlatform.Domain.VerificationType;
import com.soham.TradingPlatform.Entity.User;
import com.soham.TradingPlatform.Entity.VerificationCode;
import com.soham.TradingPlatform.Repository.VerificationRepository;
import com.soham.TradingPlatform.utils.OtpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VerificationCodeServiceImple implements VerficationCodeService{
    @Autowired
    private VerificationRepository verificationRepository;


    @Override
    public VerificationCode sendVerfication(User user, VerificationType verificationType) {
        VerificationCode verificationCode1= new VerificationCode();
        verificationCode1.setOtp(OtpUtils.generateOtp());
        verificationCode1.setVerificationType(verificationType);
        verificationCode1.setUser(user);

        return verificationRepository.save(verificationCode1);


    }

    @Override
    public VerificationCode getVerificationCodeById(Long id) throws Exception {
        Optional<VerificationCode> verificationCode=verificationRepository.findById(id);
        if(verificationCode.isPresent()){
            return verificationCode.get();
        }
        throw  new Exception("Verification code not found");




    }

    @Override
    public VerificationCode getVerificationCodeByUser(Long userId) {
        return verificationRepository.findByUserId(userId);
    }

    @Override
    public void deleteVerificationCodeById(VerificationCode verificationCode) {
        verificationRepository.delete(verificationCode);

    }
}
