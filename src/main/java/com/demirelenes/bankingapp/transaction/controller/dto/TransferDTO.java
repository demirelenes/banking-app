package com.demirelenes.bankingapp.transaction.controller.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransferDTO {

    private Long sourceAccount;

    private Long destinationAccount;

    private BigDecimal amount;
}
