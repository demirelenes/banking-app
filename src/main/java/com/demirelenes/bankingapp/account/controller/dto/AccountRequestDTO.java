package com.demirelenes.bankingapp.account.controller.dto;

import com.demirelenes.bankingapp.currency.model.CurrencyType;
import lombok.Data;

@Data
public class AccountRequestDTO {

    private CurrencyType type;

    private Long customerId;
}
