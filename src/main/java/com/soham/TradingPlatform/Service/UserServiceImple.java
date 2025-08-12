package com.soham.TradingPlatform.Service;

import com.soham.TradingPlatform.Config.JwtProvider;
import com.soham.TradingPlatform.Domain.VerificationType;
import com.soham.TradingPlatform.Entity.TwoFactorAuth;
import com.soham.TradingPlatform.Entity.User;
import com.soham.TradingPlatform.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImple implements UserService{
    @Autowired
    private UserRepository userRepository;
    @Override
    public User finduserProfileByJwt(String jwt) throws Exception {
        String email= JwtProvider.getEmailFromToken(jwt);
        User user= userRepository.findByEmail(email);
        if(user==null){
            throw new Exception("User not found");
        }
        return user;
    }

    @Override
    public User findUserByEmail(String email) throws Exception {
        User user= userRepository.findByEmail(email);
        if(user==null){
            throw new Exception("User not found");
        }
        return user;
    }

    @Override
    public User findUserById(Long userId) throws Exception {
        Optional<User>user=userRepository.findById(userId);
        if(user.isEmpty()){
            throw  new Exception("user not found");
        }
        return user.get();
    }

    @Override
    public User enableTwofactorAuth(VerificationType verificationType, String sendTo, User user) {

        TwoFactorAuth twoFactorAuth= new TwoFactorAuth();
        twoFactorAuth.setEnabled(true);
        twoFactorAuth.setSendTo(VerificationType.valueOf(sendTo));
        user.setTwoFactorAuth(twoFactorAuth);
        return userRepository.save(user);
    }



    @Override
    public User updatePassword(User user, String newPassword) {
user.setPassword(newPassword);
return userRepository.save(user);
    }
}
