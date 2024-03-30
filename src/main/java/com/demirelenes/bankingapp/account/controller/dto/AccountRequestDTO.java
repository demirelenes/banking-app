package com.demirelenes.bankingapp.account.controller.dto;

import com.demirelenes.bankingapp.currency.model.CurrencyType;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AccountRequestDTO {

    @NotNull(message = "Account Type cannot be null")
    private CurrencyType type;

    @NotNull(message = "Customer ID cannot be null")
    private Long customerId;
}
