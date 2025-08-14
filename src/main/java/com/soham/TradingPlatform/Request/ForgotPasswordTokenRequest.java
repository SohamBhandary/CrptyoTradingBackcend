package com.soham.TradingPlatform.Request;

import com.soham.TradingPlatform.Domain.VerificationType;
import lombok.Data;

@Data
public class ForgotPasswordTokenRequest {
    private String sendTo;
    private VerificationType verificationType;

}
