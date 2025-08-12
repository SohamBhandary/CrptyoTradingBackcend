package com.soham.TradingPlatform.Repository;

import com.soham.TradingPlatform.Entity.VerificationCode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationRepository extends JpaRepository<VerificationCode,Long> {

    VerificationCode findByUserId(Long userId);
}
