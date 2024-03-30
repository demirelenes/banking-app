package com.demirelenes.bankingapp.account.controller.dto;

import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class BalanceDTO {

    @Positive(message = "Amount must be positive")
    private BigDecimal amount;
}
