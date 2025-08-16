package com.soham.TradingPlatform.Service;

import com.soham.TradingPlatform.Domain.OrderType;
import com.soham.TradingPlatform.Entity.Order;
import com.soham.TradingPlatform.Entity.User;
import com.soham.TradingPlatform.Entity.Wallet;
import com.soham.TradingPlatform.Repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class WalletServiceImple implements WalletService{

    @Autowired
    private WalletRepository walletRepository;
    @Override
    public Wallet getUserWallet(User user) {
        Wallet wallet= walletRepository.findByUserId(user.getId());
        if(wallet==null){
            wallet=new Wallet();
            wallet.setUser(user);
        }
        return wallet;
    }

    @Override
    public Wallet addBalance(Wallet wallet, Long money) {
        BigDecimal balance= wallet.getBalance();
        BigDecimal newBal=balance.add(BigDecimal.valueOf(money));
        wallet.setBalance(newBal);

        return walletRepository.save(wallet);
    }

    @Override
    public Wallet findWalletById(Long id) throws Exception {
        Optional<Wallet> wallet=walletRepository.findById(id);
        if(wallet.isPresent()){
            return  wallet.get();

        }
        throw new Exception("Wallet not found");

    }

    @Override
    public Wallet walletToWalletTransfer(User sender, Wallet recevrwallet, Long amount) throws Exception {

        Wallet senderWallet=getUserWallet(sender);
        if(senderWallet.getBalance().compareTo(BigDecimal.valueOf(amount))<0){
            throw new Exception("InSufficent Balance");
        }
        BigDecimal senderBalance=senderWallet.getBalance()
                .subtract(BigDecimal.valueOf(amount));

        senderWallet.setBalance(senderBalance);
        walletRepository.save(senderWallet);

        BigDecimal receiverBal=recevrwallet.getBalance().add(BigDecimal.valueOf(amount));
        recevrwallet.setBalance(receiverBal);
        walletRepository.save(recevrwallet);
        return  senderWallet;



    }

    @Override
    public Wallet payOrderPayment(Order order, User user) throws Exception {
        Wallet wallet=getUserWallet(user);
        if(order.getOderType().equals(OrderType.BUY)){
            BigDecimal newBal=wallet.getBalance().subtract(order.getPrice());
            if(newBal.compareTo(order.getPrice())<0){
                throw new Exception("Insufficient fund");

            }
            wallet.setBalance(newBal);
        }
        else {
            BigDecimal newBalance=wallet.getBalance().add(order.getPrice());
            wallet.setBalance(newBalance);
        }
        walletRepository.save(wallet);
        return wallet;
    }
}
