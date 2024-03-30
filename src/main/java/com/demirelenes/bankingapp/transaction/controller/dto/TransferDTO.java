package com.demirelenes.bankingapp.transaction.controller.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransferDTO {

    @NotNull(message = "Source Account ID cannot be null")
    private Long sourceAccountId;

    @NotNull(message = "Destination Account ID cannot be null")
    private Long destinationAccountId;

    @Positive(message = "Amount must be positive")
    private BigDecimal amount;
}
