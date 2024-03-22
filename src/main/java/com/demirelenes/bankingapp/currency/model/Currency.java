package com.demirelenes.bankingapp.currency.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Currency {
    private CurrencyType code;
    private String name;
    private BigDecimal buyingRate;
    private BigDecimal sellingRate;
}
