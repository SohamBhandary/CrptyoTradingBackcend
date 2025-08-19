package com.soham.TradingPlatform.Repository;

import com.soham.TradingPlatform.Entity.Coin;
import com.soham.TradingPlatform.Entity.User;
import com.soham.TradingPlatform.Entity.Watchlist;

public interface WatchListService {

    Watchlist findUserWatchList(Long userId) throws Exception;
    Watchlist createWatchLis(User user);
    Watchlist findById(Long id) throws Exception;
     Coin addItemToWatch(Coin coin,User user) throws Exception;




}
