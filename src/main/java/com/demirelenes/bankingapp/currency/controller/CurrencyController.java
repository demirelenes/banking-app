package com.demirelenes.bankingapp.currency.controller;

import com.demirelenes.bankingapp.currency.model.Currency;
import com.demirelenes.bankingapp.currency.model.CurrencyType;
import com.demirelenes.bankingapp.currency.service.ICurrencyService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/currencies")
public class CurrencyController {

    private final ICurrencyService currencyService;

    public CurrencyController(ICurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping
    public List<Currency> getCurrencies() throws IOException, ParserConfigurationException, SAXException {
        return currencyService.getCurrencies();
    }

    @GetMapping("/{code}")
    public Currency getCurrencyByCode(@PathVariable("code") CurrencyType code) throws IOException, ParserConfigurationException, SAXException {
        return currencyService.getCurrencyByCode(code);
    }
}
