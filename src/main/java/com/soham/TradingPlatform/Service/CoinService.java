package com.soham.TradingPlatform.Service;

import com.soham.TradingPlatform.Entity.Coin;

import java.util.List;

public interface CoinService {
    List<Coin> getCoinList(int page);
    String getMarketChart(String coinId,int days);
    String getCoinSetails(String coinId);

    Coin findById(String coinId) throws Exception;

    String searchCoin(String keyword);

    String getTop50CoinByMarketCapRank();
    String getTradingCoins();

}
