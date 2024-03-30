package com.demirelenes.bankingapp.currency.service;

import com.demirelenes.bankingapp.currency.model.Currency;
import com.demirelenes.bankingapp.currency.model.CurrencyType;
import com.demirelenes.bankingapp.currency.service.parser.IParser;
import com.demirelenes.bankingapp.currency.service.reader.IReader;
import com.demirelenes.bankingapp.exception.CurrencyMismatchException;
import com.demirelenes.bankingapp.exception.InvalidCurrencyException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CurrencyService implements ICurrencyService {

    private final URL EXCHANGE_RATE_API;

    private volatile List<Currency> currencies;
    private volatile LocalDateTime lastAccessTime;
    private final Duration CACHE_DURATION = Duration.ofMinutes(3);

    private final IReader<Currency> reader;
    private final IParser parser;

    private final Currency TRY = new Currency(CurrencyType.TRY, "TURKISH LIRA", BigDecimal.ONE, BigDecimal.ONE);

    public CurrencyService(@Value("${exchange.rates.api.url}") String urlString, IReader<Currency> reader, IParser parser) throws MalformedURLException, URISyntaxException {
        EXCHANGE_RATE_API = new URI(urlString).toURL();
        this.reader = reader;
        this.parser = parser;
    }

    @Override
    public List<Currency> getCurrencies() throws IOException, ParserConfigurationException, SAXException {
        if (invalidCache()) refreshCache();
        return currencies;
    }

    @Override
    public Currency getCurrencyByCode(CurrencyType code) throws IOException, ParserConfigurationException, SAXException {
        if (code == null) throw new IllegalArgumentException("Currency code cannot be null!");
        if (invalidCache()) refreshCache();
        if (code == CurrencyType.TRY) return TRY;
        return currencies.stream()
                .filter(c -> c.getCode().equals(code))
                .findFirst()
                .orElseThrow(() -> new InvalidCurrencyException(code + " is not a valid currency type!"));
    }

    @Override
    public BigDecimal getExchangeRate(CurrencyType sourceType, CurrencyType destinationType) throws IOException, ParserConfigurationException, SAXException {
        if (sourceType == null || destinationType == null) throw new IllegalArgumentException("Currency type cannot be null!");
        if (sourceType == CurrencyType.TRY) {
            Currency currency = getCurrencyByCode(destinationType);
            return BigDecimal.ONE.divide(currency.getSellingRate(), RoundingMode.HALF_EVEN);
        } else if (destinationType == CurrencyType.TRY) {
            Currency currency = getCurrencyByCode(sourceType);
            return currency.getBuyingRate();
        } else {
            throw new CurrencyMismatchException();
        }
    }

    private synchronized boolean invalidCache() {
        return lastAccessTime == null || currencies == null || Duration.between(lastAccessTime, LocalDateTime.now()).toMinutes() >= CACHE_DURATION.toMinutes();
    }

    @SuppressWarnings("unchecked")
    private synchronized void refreshCache() throws IOException, ParserConfigurationException, SAXException {
        var parsedCurrencies = parser.parse(EXCHANGE_RATE_API.openStream());
        lastAccessTime = LocalDateTime.now();
        currencies = (List<Currency>) reader.read(parsedCurrencies);
    }
}
