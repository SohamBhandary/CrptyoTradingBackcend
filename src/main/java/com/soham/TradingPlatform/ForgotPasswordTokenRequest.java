package com.soham.TradingPlatform;

import lombok.Data;

@Data
public class ForgotPasswordTokenRequest {
    private String sendTo;
    private String otp;

}
