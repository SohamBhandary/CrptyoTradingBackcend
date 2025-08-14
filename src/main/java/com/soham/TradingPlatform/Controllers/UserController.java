package com.soham.TradingPlatform.Controllers;


import com.soham.TradingPlatform.Domain.VerificationType;
import com.soham.TradingPlatform.Entity.ForgotPasswordToken;
import com.soham.TradingPlatform.Entity.User;
import com.soham.TradingPlatform.Entity.VerificationCode;
import com.soham.TradingPlatform.Service.EmailService;
import com.soham.TradingPlatform.Service.ForgotPasswordService;
import com.soham.TradingPlatform.Service.UserService;
import com.soham.TradingPlatform.Service.VerficationCodeService;
import com.soham.TradingPlatform.utils.OtpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

    private String jwt;
    @Autowired
    private VerficationCodeService verficationCodeService;
    @Autowired
    private ForgotPasswordService forgotPasswordService;

    @GetMapping("/api/users/profile")
    public ResponseEntity<User> getUserProfile (@RequestHeader ("Authorization")String jwt) throws Exception {
        User user=userService.finduserProfileByJwt(jwt);


        return new ResponseEntity<User>(user, HttpStatus.OK);
    }
    @PostMapping("/api/users/verfication/{verificationType}/send-otp")
    public ResponseEntity<String> sendVerificationOtp(@RequestHeader("Authrization")String jwt
            ,@PathVariable VerificationType verificationType) throws Exception{

        User user= userService.finduserProfileByJwt(jwt);
        VerificationCode verificationCode= verficationCodeService.getVerificationCodeByUser(user.getId());

        if(verificationCode==null){
            verificationCode=verficationCodeService.sendVerfication(user,verificationType);
        }
        if(verificationType.equals(verificationType.EMAIL)){
            emailService.sendVerficationOtpEmail(user.getEmail(),verificationCode.getOtp());
        }


        return new ResponseEntity<>("Verfication code sent succefully",HttpStatus.OK);
    }



    @PatchMapping("/api/users/enable-twofactor/verify-otp/{otp}")
    public ResponseEntity<User> enableTwofactorAuth (@PathVariable String otp ,@RequestHeader ("Authorization")String jwt) throws Exception {
        User user=userService.finduserProfileByJwt(jwt);
        VerificationCode verificationCode=verficationCodeService.getVerificationCodeByUser(user.getId());
        String sendTo=verificationCode.getVerificationType().equals(VerificationType.EMAIL)?
        verificationCode.getEmail():verificationCode.getMobile();
        boolean isVerified=verificationCode.getOtp().equals(otp);
        if(isVerified){
            User updatedUser=userService.enableTwofactorAuth(verificationCode.getVerificationType(),sendTo,user);
            verficationCodeService.deleteVerificationCodeById(verificationCode);
            return  new ResponseEntity<>(updatedUser,HttpStatus.OK);

        }
        throw  new Exception("Wrong otp");



    }
    @PostMapping("/auth/users/reset-password/send-otp")
    public ResponseEntity<String> sendForgotPasswordOtp(@RequestHeader("Authrization")String jwt
            ,@PathVariable VerificationType verificationType) throws Exception{
        User user= userService.finduserProfileByJwt(jwt);
        String otp= OtpUtils.generateOtp();
        UUID uuid=UUID.randomUUID();
        String id =uuid.toString();
        ForgotPasswordToken token= forgotPasswordService.findById(String.valueOf(user.getId()));
        if(token==null){
            token=forgotPasswordService.createToken(user,id,)
        }






        return new ResponseEntity<>("ForgotPassword otp sent succefully",HttpStatus.OK);
    }



}
