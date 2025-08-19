package com.soham.TradingPlatform.Repository;

import com.soham.TradingPlatform.Entity.Withdrawl;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WithDrawlRepository extends JpaRepository<Withdrawl,Long> {
    List<Withdrawl> findByUserId(Long userId);
}
