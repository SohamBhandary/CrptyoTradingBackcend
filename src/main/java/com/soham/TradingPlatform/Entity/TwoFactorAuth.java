package com.soham.TradingPlatform.Entity;

import com.soham.TradingPlatform.Domain.VerificationType;
import lombok.Data;

@Data
public class TwoFactorAuth {
    private boolean isEnabled=false;
    private VerificationType sendTo;

}
