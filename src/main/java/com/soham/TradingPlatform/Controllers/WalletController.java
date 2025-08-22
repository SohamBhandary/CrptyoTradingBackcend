package com.soham.TradingPlatform.Controllers;

import com.soham.TradingPlatform.Entity.*;
import com.soham.TradingPlatform.Response.PaymentResponse;
import com.soham.TradingPlatform.Service.OrderService;
import com.soham.TradingPlatform.Service.PaymentService;
import com.soham.TradingPlatform.Service.UserService;
import com.soham.TradingPlatform.Service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController

public class WalletController {
    @Autowired
    private WalletService walletService;
    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/api/wallet")
    public ResponseEntity<Wallet> getUserWallet(@RequestHeader("Authorization") String jwt) throws Exception {
        User user=userService.finduserProfileByJwt(jwt);

        Wallet wallet=walletService.getUserWallet(user);
        return new ResponseEntity<>(wallet, HttpStatus.ACCEPTED);

    }

    @PutMapping("/api/wallet/{walletId}/transfer")
    public ResponseEntity<Wallet> walletToWalletTransfer(@RequestHeader("Authorization") String jwt,
                                                         @PathVariable Long walletId,
                                                         @RequestBody WalletTransaction req) throws Exception {
        User senderUser=userService.findUserById(Long.valueOf(jwt));
        Wallet receieverWallet=walletService.findWalletById(walletId);
        Wallet wallet=walletService.walletToWalletTransfer(senderUser,receieverWallet,req.getAmount());
        return new ResponseEntity<>(wallet,HttpStatus.ACCEPTED);


    }
    @PutMapping("/api/wallet/order/{orderId}/pay")
    public ResponseEntity<Wallet> payOrderPayment(@RequestHeader("Authorization") String jwt,
                                                         @PathVariable Long orderId
                                                         ) throws Exception {
        User user=userService.findUserById(Long.valueOf(jwt));
        Order order=orderService.getOrderById(orderId);
        Wallet wallet=walletService.payOrderPayment(order,user);
   return  new ResponseEntity<>(wallet,HttpStatus.ACCEPTED);


    }

    @PutMapping("/api/wallet/deposit/")
    public ResponseEntity<Wallet> addBalanceToWallet(@RequestHeader("Authorization") String jwt,
                                                   @RequestParam(name="order_id") Long orderId,
                                                  @RequestParam(name="payment_id") String paymentId



    ) throws Exception {
        User user=userService.findUserById(Long.valueOf(jwt));

        Wallet wallet=walletService.getUserWallet(user);
        PaymentOrder order=paymentService.getPaymentOrderById(orderId);
        Boolean status=paymentService.proccedPaymentOrder(order,paymentId);
        PaymentResponse res= new PaymentResponse();
        res.setPayment_url("deposit success");
        if(status){
            wallet=walletService.addBalance(wallet,order.getAmount());
        }
        return  new ResponseEntity<>(wallet,HttpStatus.ACCEPTED);


    }




}
