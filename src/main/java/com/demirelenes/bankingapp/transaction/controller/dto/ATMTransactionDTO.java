package com.demirelenes.bankingapp.transaction.controller.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ATMTransactionDTO {

    private Boolean isDeposit;

    private BigDecimal amount;

    private Long sourceAccountId;
}
