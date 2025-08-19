package com.soham.TradingPlatform.Controllers;

import com.soham.TradingPlatform.Entity.User;
import com.soham.TradingPlatform.Entity.Wallet;
import com.soham.TradingPlatform.Entity.Withdrawl;
import com.soham.TradingPlatform.Service.UserService;
import com.soham.TradingPlatform.Service.WalletService;
import com.soham.TradingPlatform.Service.WithdrawlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class WithdrawlController {
    @Autowired
    private WithdrawlService withdrawlService;

    @Autowired
    private WalletService walletService;

    @Autowired
    private UserService userService;

//    @Autowired
//    private WalletTransactionService walletTransactionService;

    @PostMapping("/api/withdrawal/{amount}")
    public ResponseEntity<?> withdrawalRequest(
            @PathVariable Long amount,
            @RequestHeader("Authorization")String jwt) throws Exception {
        User user=userService.finduserProfileByJwt(jwt);
        Wallet userWallet=walletService.getUserWallet(user);

       Withdrawl withdrawl=withdrawlService.requestWithDrawl(amount,user);
        walletService.addBalance(userWallet, -withdrawl.getAmount());

//        WalletTransaction walletTransaction = walletTransactionService.createTransaction(
//                userWallet,
//                WalletTransactionType.WITHDRAWAL,null,
//                "bank account withdrawal",
//                withdrawal.getAmount()
//        );

        return new ResponseEntity<>(withdrawl, HttpStatus.OK);
    }

    @PatchMapping("/api/admin/withdrawal/{id}/proceed/{accept}")
    public ResponseEntity<?> proceedWithdrawal(
            @PathVariable Long id,
            @PathVariable boolean accept,
            @RequestHeader("Authorization")String jwt) throws Exception {
        User user=userService.finduserProfileByJwt(jwt);

        Withdrawl withdrawl=withdrawlService.proceedWithDrawl(id,accept);

        Wallet userWallet=walletService.getUserWallet(user);
        if(!accept){
            walletService.addBalance(userWallet, withdrawl.getAmount());
        }

        return new ResponseEntity<>(withdrawl, HttpStatus.OK);
    }

    @GetMapping("/api/withdrawal")
    public ResponseEntity<List<Withdrawl>> getWithdrawalHistory(

            @RequestHeader("Authorization")String jwt) throws Exception {
        User user=userService.finduserProfileByJwt(jwt);

        List<Withdrawl> withdrawal=withdrawlService.getUSerWithdrawlHisotry(user);

        return new ResponseEntity<>(withdrawal, HttpStatus.OK);
    }

    @GetMapping("/api/admin/withdrawal")
    public ResponseEntity<List<Withdrawl>> getAllWithdrawalRequest(

            @RequestHeader("Authorization")String jwt) throws Exception {
        User user=userService.finduserProfileByJwt(jwt);

        List<Withdrawl> withdrawal=withdrawlService.getAllWithdrwalRequest();

        return new ResponseEntity<>(withdrawal, HttpStatus.OK);
    }
}
