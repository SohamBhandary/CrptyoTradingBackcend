package com.soham.TradingPlatform.Controllers;

import com.soham.TradingPlatform.Domain.OrderType;
import com.soham.TradingPlatform.Entity.Coin;
import com.soham.TradingPlatform.Entity.Order;
import com.soham.TradingPlatform.Entity.User;
import com.soham.TradingPlatform.Request.CreateOrderRequest;
import com.soham.TradingPlatform.Service.CoinService;
import com.soham.TradingPlatform.Service.OrderService;
import com.soham.TradingPlatform.Service.UserService;
import com.soham.TradingPlatform.Service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userSerivce;

    @Autowired
    private CoinService coinService;

    @Autowired
    private WalletService walletTransactionService;

    @Autowired
    public OrderController(OrderService orderService, UserService userSerivce) {
        this.orderService = orderService;
        this.userSerivce=userSerivce;
    }

    @PostMapping("/pay")
    public ResponseEntity<Order> payOrderPayment(
            @RequestHeader("Authorization") String jwt,
            @RequestBody CreateOrderRequest req

    ) throws Exception {
        User user = userSerivce.finduserProfileByJwt(jwt);
        Coin coin =coinService.findById(req.getCoinId());


        Order order = orderService.processOrder(coin,req.getQuantity(),req.getOrderType(),user);

        return ResponseEntity.ok(order);

    }

    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getOrderById(
            @RequestHeader("Authorization") String jwtToken,
            @PathVariable Long orderId
    ) throws Exception {


        User user = userSerivce.finduserProfileByJwt(jwtToken);

        Order order = orderService.getOrderById(orderId);
        if (order.getUser().getId().equals(user.getId())) {
            return ResponseEntity.ok(order);
        } else {
           throw  new Exception("You dont have access");
        }
    }

    @GetMapping()
    public ResponseEntity<List<Order>> getAllOrdersForUser(
            @RequestHeader("Authorization") String jwtToken,
            @RequestParam(required = false) OrderType order_type,
            @RequestParam(required = false) String asset_symbol
    ) throws Exception {
        if (jwtToken == null) {
            throw new Exception("token missing...");
        }

        Long userId = userSerivce.finduserProfileByJwt(jwtToken).getId();

        List<Order> userOrders = orderService.getAllOrders(userId,order_type,asset_symbol);
        return ResponseEntity.ok(userOrders);
    }




}
