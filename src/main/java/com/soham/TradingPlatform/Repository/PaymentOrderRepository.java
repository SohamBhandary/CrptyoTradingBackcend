package com.soham.TradingPlatform.Repository;

import com.soham.TradingPlatform.Entity.PaymentOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentOrderRepository extends JpaRepository<PaymentOrder,Long> {
}
