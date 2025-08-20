package com.soham.TradingPlatform.Entity;

import com.soham.TradingPlatform.Domain.PaymentMethod;
import com.soham.TradingPlatform.Domain.PaymentOrderStatus;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data

public class PaymentOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long amount;

    private PaymentOrderStatus status;
    private PaymentMethod method;

    @ManyToOne
    private  User user;


}
