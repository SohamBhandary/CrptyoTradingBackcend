package com.soham.TradingPlatform.Request;

import com.soham.TradingPlatform.Domain.OrderType;
import lombok.Data;

@Data

public class CreateOrderRequest {
    private String coinId;
    private double quantity;
    private OrderType orderType;
}
