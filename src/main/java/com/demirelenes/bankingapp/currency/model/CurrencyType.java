package com.demirelenes.bankingapp.currency.model;

import lombok.Getter;

@Getter
public enum CurrencyType {
    TRY("TRY"),
    USD("USD"),
    AUD("AUD"),
    DKK("DKK"),
    EUR("EUR"),
    GBP("GBP"),
    CHF("CHF"),
    SEK("SEK"),
    CAD("CAD"),
    KWD("KWD"),
    NOK("NOK"),
    SAR("SAR"),
    JPY("JPY"),
    BGN("BGN"),
    GBN("GBN"),
    RON("RON"),
    RUB("RUB"),
    IRR("IRR"),
    CNY("CNY"),
    PKR("PKR"),
    QAR("QAR"),
    KRW("KRW"),
    AZN("AZN"),
    AED("AED");

    private final String code;

    CurrencyType(String code) {
        this.code = code;
    }
}
