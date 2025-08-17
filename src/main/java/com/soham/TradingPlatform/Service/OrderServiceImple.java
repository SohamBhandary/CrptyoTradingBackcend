package com.soham.TradingPlatform.Service;

import com.soham.TradingPlatform.Domain.OrderStatus;
import com.soham.TradingPlatform.Domain.OrderType;
import com.soham.TradingPlatform.Entity.Coin;
import com.soham.TradingPlatform.Entity.Order;
import com.soham.TradingPlatform.Entity.OrderItem;
import com.soham.TradingPlatform.Entity.User;
import com.soham.TradingPlatform.Repository.OrderItemRepository;
import com.soham.TradingPlatform.Repository.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderServiceImple implements  OrderService{
    @Autowired
    private WalletService walletService;
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;


    @Override
    public Order createOrder(User user, OrderItem orderItem, OrderType orderType) {
        double price=orderItem.getCoin().getCurrentPrice()*orderItem.getQuantity();
        Order order= new Order();
        order.setUser(user);
        order.setOrderItem(orderItem);
        order.setOderType(orderType);
        order.setPrice(BigDecimal.valueOf(price));
        order.setTimestamp(LocalDateTime.now());
        order.setStatus(OrderStatus.PENDING);


        return orderRepository.save(order);
    }

    @Override
    public Order getOrderById(Long orderId) throws Exception {
        return orderRepository.findById(orderId)
                .orElseThrow(()-> new Exception("order nor found"));
    }

    @Override
    public List<Order> getAllOrders(Long userId, OrderType orderType, String assestSymbol) {
        return orderRepository.findByUserId(userId);
    }

    private OrderItem createOrderItem(Coin coin,double quantity,
                                      double buyPrice,double sellPrice){

        OrderItem orderItem= new OrderItem();
        orderItem.setCoin(coin);
        orderItem.setQuantity(quantity);
        orderItem.setBuyPrice(buyPrice);
        orderItem.setSellPrice(sellPrice);
        return orderItemRepository.save(orderItem);
    }
    @Transactional
    public Order buyAssest(Coin coin,double quantity,User user) throws Exception {
        if(quantity<=0){
            throw  new Exception("quantityt should be greater than zero");
        }
        double buyPrice=coin.getCurrentPrice();

        OrderItem orderItem=createOrderItem(coin,quantity,buyPrice,0);
        Order order= createOrder(user,orderItem,OrderType.BUY);
        orderItem.setOrder(order);

        walletService.payOrderPayment(order,user);
        order.setStatus(OrderStatus.SUCCESS);
        order.setOderType(OrderType.BUY);
        Order savedOrder=orderRepository.save(order);
        return savedOrder;

    }
    @Transactional
    public Order sellAssest(Coin coin,double quantity,User user) throws Exception {
        if(quantity<=0){
            throw  new Exception("quantityt should be greater than zero");
        }
        double sellPrice=coin.getCurrentPrice();
        double buyPrice=assetToSell.getPrice();


        OrderItem orderItem=createOrderItem(coin,quantity,buyPrice,0);
        Order order= createOrder(user,orderItem,OrderType.SELL);
        orderItem.setOrder(order);
        if(assetToSell.getQuanity()>=quantity){
            order.setStatus(OrderStatus.SUCCESS);
            order.setOderType(OrderType.SELL);
            Order savedOrder=orderRepository.save(order);
            return savedOrder;

            walletService.payOrderPayment(order,user);

            Assest updatedAsset=assestService.updateAssest(assetToSell.getId()-quantity);
            if(updatedAsset.getQuantity()*coin.getCurrentPrice()<=1){
                assestService.deleteAssest(updatedAsset.getId);

            }
            return savedOrder;

        }
        throw new Exception("Insufficient Quanityt");



    }


    @Override
    @Transactional
    public Order processOrder(Coin coin, double quantity, OrderType orderType, User user) throws Exception {
        if(orderType==OrderType.BUY){
            return buyAssest(coin,quantity,user);
        } else if (orderType.equals(OrderType.SELL)) {
            return sellAssest(coin,quantity,user);

            
        }
        throw  new Exception("Invalid Order type");
    }
}
