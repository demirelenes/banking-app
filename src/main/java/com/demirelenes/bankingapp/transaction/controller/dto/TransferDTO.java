package com.demirelenes.bankingapp.transaction.controller.dto;

import com.demirelenes.bankingapp.account.entity.Account;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransferDTO {

    private Account sourceAccount;

    private Account destinationAccount;

    private BigDecimal amount;
}
