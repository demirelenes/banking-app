package com.demirelenes.bankingapp.account.controller.dto;

import com.demirelenes.bankingapp.currency.controller.model.CurrencyType;
import lombok.Data;

@Data
public class AccountRequestDTO {

    private CurrencyType type;

    private Long customerId;
}
