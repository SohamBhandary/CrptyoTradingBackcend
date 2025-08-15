package com.soham.TradingPlatform.Service;

import com.soham.TradingPlatform.Entity.Coin;

import java.util.List;

public interface CoinService {
    List<Coin> getCoinList(int page);
    String getMarketChart(String coinId,int days);
    String getCoinSetails(String coinId);

    String searchCoin(String keyword);

    String getTop50CoinByMarketCapRank();

}
