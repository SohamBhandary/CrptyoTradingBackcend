package com.soham.TradingPlatform.Service;

import com.soham.TradingPlatform.Entity.Order;
import com.soham.TradingPlatform.Entity.User;
import com.soham.TradingPlatform.Entity.Wallet;

public interface WalletService {
    Wallet getUserWallet(User user);
    Wallet addBalance(Wallet wallet,Long money);
    Wallet findWalletById(Long id) throws Exception;
    Wallet walletToWalletTransfer(User sender,Wallet recevrwallet,Long amount) throws Exception;
    Wallet payOrderPayment(Order order, User user);
}
