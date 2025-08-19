package com.soham.TradingPlatform.Repository;

import com.soham.TradingPlatform.Entity.Watchlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Jpa21Utils;

public interface WatchListRepository extends JpaRepository<Watchlist,Long> {
    Watchlist findByUserId(Long userId);

}
