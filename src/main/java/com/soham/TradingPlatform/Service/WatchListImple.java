package com.soham.TradingPlatform.Service;

import com.soham.TradingPlatform.Entity.Coin;
import com.soham.TradingPlatform.Entity.User;
import com.soham.TradingPlatform.Entity.Watchlist;
import com.soham.TradingPlatform.Repository.WatchListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class WatchListImple implements WatchListService {

    @Autowired
    private WatchListRepository watchListRepository;
    @Override
    public Watchlist findUserWatchList(Long userId) throws Exception {
        Watchlist watchlist=watchListRepository.findByUserId(userId);
        if(watchlist==null ){
          throw new Exception("watchlist not found");
        }
        return watchlist ;
    }

    @Override
    public Watchlist createWatchLis(User user) {
        Watchlist watchlist= new Watchlist();
        watchlist.setUser(user);
        return watchListRepository.save(watchlist);
    }

    @Override
    public Watchlist findById(Long id) throws Exception {
        Optional<Watchlist> watchlistOptional=watchListRepository.findById(id);
        if(watchlistOptional.isEmpty()){
            throw  new Exception("watchlist not found");
        }
        return watchlistOptional.get();
    }

    @Override
    public Coin addItemToWatch(Coin coin, User user) throws Exception {
        Watchlist watchlist=findUserWatchList(user.getId());
        if(watchlist.getCoins().contains(coin)){
            watchlist.getCoins().remove(coin);
        }
        else watchlist.getCoins().add(coin);
       watchListRepository.save(watchlist);
        return  coin;

    }
}
