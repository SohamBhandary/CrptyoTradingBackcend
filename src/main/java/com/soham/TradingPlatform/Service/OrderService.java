package com.soham.TradingPlatform.Service;

import com.soham.TradingPlatform.Domain.OrderType;
import com.soham.TradingPlatform.Entity.Coin;
import com.soham.TradingPlatform.Entity.Order;
import com.soham.TradingPlatform.Entity.OrderItem;
import com.soham.TradingPlatform.Entity.User;

import java.util.List;

public interface OrderService {


    Order createOrder(User user, OrderItem orderItem, OrderType orderType);
    Order getOrderById(Long orderId) throws Exception;
    List<Order> getAllOrders(Long userId, OrderType orderType, String assestSymbol);
    Order processOrder(Coin coin,double quantity,OrderType orderType,User user) throws Exception;







}
