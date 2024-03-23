package com.demirelenes.bankingapp.currency.service;

import com.demirelenes.bankingapp.currency.model.Currency;
import com.demirelenes.bankingapp.currency.model.CurrencyType;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public interface ICurrencyService {

    List<Currency> getCurrencies() throws IOException, ParserConfigurationException, SAXException;

    Currency getCurrencyByCode(CurrencyType code) throws IOException, ParserConfigurationException, SAXException;

    BigDecimal getExchangeRate(CurrencyType sourceType, CurrencyType destinationType)  throws IOException, ParserConfigurationException, SAXException;
}
