package com.soham.TradingPlatform.Repository;

import com.soham.TradingPlatform.Entity.ForgotPasswordToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ForgotPasswordRepository extends JpaRepository<ForgotPasswordToken,Long> {
    ForgotPasswordToken findByUserId(Long userId);
}
