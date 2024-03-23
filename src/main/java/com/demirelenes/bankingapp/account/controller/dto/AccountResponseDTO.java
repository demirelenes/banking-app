package com.demirelenes.bankingapp.account.controller.dto;

import com.demirelenes.bankingapp.currency.model.CurrencyType;
import com.demirelenes.bankingapp.transaction.controller.dto.TransactionResponseDTO;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class AccountResponseDTO {

    private Long id;

    private CurrencyType type;

    private BigDecimal balance;

    private Long customerId;

    private List<TransactionResponseDTO> transactions;
}
