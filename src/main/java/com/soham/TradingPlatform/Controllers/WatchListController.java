package com.soham.TradingPlatform.Controllers;

import com.soham.TradingPlatform.Entity.Coin;
import com.soham.TradingPlatform.Entity.User;
import com.soham.TradingPlatform.Entity.Watchlist;
import com.soham.TradingPlatform.Service.WatchListService;
import com.soham.TradingPlatform.Service.CoinService;
import com.soham.TradingPlatform.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/watchlist")

public class WatchListController {
    private  WatchListService watchlistService;
    private UserService userService;

    @Autowired
    private CoinService coinService;



    @GetMapping("/user")
    public ResponseEntity<Watchlist> getUserWatchlist(
            @RequestHeader("Authorization") String jwt) throws Exception {

        User user=userService.finduserProfileByJwt(jwt);
        Watchlist watchlist = watchlistService.findUserWatchList(user.getId());
        return ResponseEntity.ok(watchlist);

    }


    public ResponseEntity<Watchlist> createWatchlist(
            @RequestHeader("Authorization") String jwt) throws Exception {
        User user=userService.finduserProfileByJwt(jwt);
        Watchlist createdWatchlist = watchlistService.createWatchLis(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdWatchlist);
    }

    @GetMapping("/{watchlistId}")
    public ResponseEntity<Watchlist> getWatchlistById(
            @PathVariable Long watchlistId) throws Exception {

        Watchlist watchlist = watchlistService.findById(watchlistId);
        return ResponseEntity.ok(watchlist);

    }

    @PatchMapping("/add/coin/{coinId}")
    public ResponseEntity<Coin> addItemToWatchlist(
            @RequestHeader("Authorization") String jwt,
            @PathVariable String coinId) throws Exception {


        User user=userService.finduserProfileByJwt(jwt);
        Coin coin=coinService.findById(coinId);
        Coin addedCoin = watchlistService.addItemToWatch(coin, user);
        return ResponseEntity.ok(addedCoin);

    }
}
