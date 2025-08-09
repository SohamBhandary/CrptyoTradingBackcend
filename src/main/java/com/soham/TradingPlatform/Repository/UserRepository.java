package com.soham.TradingPlatform.Repository;

import com.soham.TradingPlatform.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
