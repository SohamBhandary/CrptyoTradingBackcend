package com.soham.TradingPlatform.Repository;

import com.soham.TradingPlatform.Entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem,Long> {
}
