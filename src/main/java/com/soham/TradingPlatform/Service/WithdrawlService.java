package com.soham.TradingPlatform.Service;

import com.soham.TradingPlatform.Entity.User;
import com.soham.TradingPlatform.Entity.Withdrawl;

import java.util.List;

public interface WithdrawlService {

    Withdrawl requestWithDrawl(Long amount, User user);

    Withdrawl proceedWithDrawl(Long withdrwalId,boolean accept)  throws Exception ;

    List<Withdrawl> getUSerWithdrawlHisotry(User user);

    List<Withdrawl>getAllWithdrwalRequest();





}
