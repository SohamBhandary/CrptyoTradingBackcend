package com.soham.TradingPlatform.Service;

import com.soham.TradingPlatform.Domain.VerificationType;
import com.soham.TradingPlatform.Entity.User;

public interface UserService {
    User finduserProfileByJwt(String jwt) throws Exception;
    User findUserByEmail(String email) throws Exception;
    User findUserById(Long userId) throws Exception;


     User enableTwofactorAuth(VerificationType verificationType,
                              String sendTo, User user);

    User updatePassword(User user,String newPassword);
}
