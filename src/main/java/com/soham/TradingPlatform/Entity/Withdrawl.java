package com.soham.TradingPlatform.Entity;

import com.soham.TradingPlatform.Domain.WithdrawlStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
public class Withdrawl {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private WithdrawlStatus status;

    private  Long amount;
    @ManyToOne
    private  User  user;
    private LocalDateTime date=LocalDateTime.now();


}
