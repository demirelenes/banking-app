package com.demirelenes.bankingapp.currency.service;

import com.demirelenes.bankingapp.currency.controller.model.Currency;
import com.demirelenes.bankingapp.currency.controller.model.CurrencyType;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CurrencyService implements ICurrencyService {
    @Override
    public List<Currency> getCurrencies() {
        return null;
    }

    @Override
    public Currency getCurrencyByCode(CurrencyType code) {
        return null;
    }
}
