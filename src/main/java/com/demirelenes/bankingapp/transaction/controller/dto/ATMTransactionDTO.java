package com.demirelenes.bankingapp.transaction.controller.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ATMTransactionDTO {

    @NotNull(message = "Transaction action must be stated as true for Deposit or false for Withdraw")
    private Boolean isDeposit;

    @Positive(message = "Amount must be positive")
    private BigDecimal amount;

    @NotNull(message = "Source Account ID cannot be null")
    private Long sourceAccountId;
}
