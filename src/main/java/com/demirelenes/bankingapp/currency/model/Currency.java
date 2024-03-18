package com.demirelenes.bankingapp.currency.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Currency {
    private CurrencyType code;
    private String name;
    private BigDecimal buyingRate;
    private BigDecimal sellingRate;
}
