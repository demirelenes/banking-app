package com.demirelenes.bankingapp.account.controller.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class BalanceDTO {

    private BigDecimal amount;
}
