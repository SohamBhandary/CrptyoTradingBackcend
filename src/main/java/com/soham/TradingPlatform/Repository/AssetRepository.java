package com.soham.TradingPlatform.Repository;

import com.soham.TradingPlatform.Entity.Assest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssetRepository extends JpaRepository<Assest,Long> {
    List<Assest> findByUserId(Long userId);
    Assest findByUserIdAndCoinId(Long userId,String coinId);
}
