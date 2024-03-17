package com.demirelenes.bankingapp.account.controller.dto;

import com.demirelenes.bankingapp.currency.controller.model.CurrencyType;
import com.demirelenes.bankingapp.transaction.entity.Transaction;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class AccountResponseDTO {

    private Long id;

    private CurrencyType type;

    private BigDecimal balance;

    private Long customerId;

    private List<Transaction> transactions;
}
