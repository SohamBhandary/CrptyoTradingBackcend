package com.soham.TradingPlatform.Repository;

import com.soham.TradingPlatform.Entity.Coin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoinRepository extends JpaRepository<Coin,String> {
}
