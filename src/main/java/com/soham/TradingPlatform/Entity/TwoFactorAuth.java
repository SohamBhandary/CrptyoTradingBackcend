package com.soham.TradingPlatform.Entity;

import com.soham.TradingPlatform.Domain.verificationType;
import lombok.Data;

@Data
public class TwoFactorAuth {
    private boolean isEnabled=false;
    private verificationType sendTo;

}
