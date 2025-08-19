package com.soham.TradingPlatform.Service;

import com.soham.TradingPlatform.Domain.WithdrawlStatus;
import com.soham.TradingPlatform.Entity.User;
import com.soham.TradingPlatform.Entity.Withdrawl;
import com.soham.TradingPlatform.Repository.WithDrawlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class WithdrawlServiceImple implements WithdrawlService {
    @Autowired
    private WithDrawlRepository withDrawlRepository;

    @Override
    public Withdrawl requestWithDrawl(Long amount, User user) {
        Withdrawl withdrawl= new Withdrawl();
        withdrawl.setAmount(amount);
        withdrawl.setUser(user);
        withdrawl.setStatus(WithdrawlStatus.PENDING);


        return withDrawlRepository.save(withdrawl);
    }

    @Override
    public Withdrawl proceedWithDrawl(Long withdrwalId, boolean accept) throws Exception {
        Optional<Withdrawl> withdrawl=withDrawlRepository.findById(withdrwalId);
        if(withdrawl.isEmpty()){
            throw new Exception("withdrwal not found");
        }
        Withdrawl withdrawl1=withdrawl.get();
        withdrawl1.setDate(LocalDateTime.now());
        if(accept){
            withdrawl1.setStatus(WithdrawlStatus.SUCCESS);
        }
        else {
            withdrawl1.setStatus(WithdrawlStatus.PENDING);
        }


        return withDrawlRepository.save(withdrawl1) ;
    }

    @Override
    public List<Withdrawl> getUSerWithdrawlHisotry(User user) {
        return withDrawlRepository.findByUserId(user.getId());
    }

    @Override
    public List<Withdrawl> getAllWithdrwalRequest() {
        return withDrawlRepository.findAll();
    }
}
