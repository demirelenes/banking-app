package com.demirelenes.bankingapp.currency.service;

import com.demirelenes.bankingapp.currency.controller.model.Currency;
import com.demirelenes.bankingapp.currency.controller.model.CurrencyType;

import java.util.List;

public interface ICurrencyService {

    List<Currency> getCurrencies();

    Currency getCurrencyByCode(CurrencyType code);
}
