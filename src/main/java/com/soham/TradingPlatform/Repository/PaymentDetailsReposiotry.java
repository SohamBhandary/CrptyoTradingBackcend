package com.soham.TradingPlatform.Repository;

import com.soham.TradingPlatform.Entity.PaymentDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentDetailsReposiotry extends JpaRepository<PaymentDetails,Long> {
PaymentDetails findByUserId(Long userId);



}
