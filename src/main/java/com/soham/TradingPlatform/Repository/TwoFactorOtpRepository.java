package com.soham.TradingPlatform.Repository;

import com.soham.TradingPlatform.Entity.TwoFactorOTP;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TwoFactorOtpRepository extends JpaRepository <TwoFactorOTP,String>{

    TwoFactorOTP findByUserId(Long userId);
}
