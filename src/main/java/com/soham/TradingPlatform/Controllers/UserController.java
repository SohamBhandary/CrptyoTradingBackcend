package com.soham.TradingPlatform.Controllers;


import com.soham.TradingPlatform.Domain.VerificationType;
import com.soham.TradingPlatform.Entity.User;
import com.soham.TradingPlatform.Service.EmailService;
import com.soham.TradingPlatform.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

    private String jwt;

    @GetMapping("/api/users/profile")
    public ResponseEntity<User> getUserProfile (@RequestHeader ("Authorization")String jwt) throws Exception {
        User user=userService.finduserProfileByJwt(jwt);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }
    @PostMapping("/api/users/verification{verificationtype}/send-otp")
    public ResponseEntity<User> sendVerifiacationOtp (@RequestHeader ("Authorization")String jwt,
                                                      @PathVariable VerificationType verificationType)
            throws Exception {

        User user=userService.finduserProfileByJwt(jwt);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }
    @PatchMapping("/api/users/enable-twofactor/verify-otp/{otp}")
    public ResponseEntity<User> enableTwofactorAuth (@RequestHeader ("Authorization")String jwt) throws Exception {
        User user=userService.finduserProfileByJwt(jwt);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }



}
