package com.soham.TradingPlatform.Service;

import com.soham.TradingPlatform.Entity.User;

public interface UserService {
    User finduserProfileByJwt(String jwt);
    User findUserByEmail(String email);
}
