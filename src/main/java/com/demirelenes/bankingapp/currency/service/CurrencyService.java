package com.demirelenes.bankingapp.currency.service;

import com.demirelenes.bankingapp.currency.model.Currency;
import com.demirelenes.bankingapp.currency.model.CurrencyType;
import com.demirelenes.bankingapp.currency.service.parser.IParser;
import com.demirelenes.bankingapp.currency.service.reader.IReader;
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

    private final URL EXCHANGE_RATE_API = new URI("https://www.tcmb.gov.tr/kurlar/today.xml").toURL();

    private volatile List<Currency> currencies;
    private volatile LocalDateTime lastAccessTime;
    private final Duration CACHE_DURATION = Duration.ofMinutes(3);

    private final IReader<Currency> reader;
    private final IParser parser;

    private final Currency TRY = new Currency(CurrencyType.TRY, "TURKISH LIRA", BigDecimal.ONE, BigDecimal.ONE);

    public CurrencyService(IReader<Currency> reader, IParser parser) throws MalformedURLException, URISyntaxException {
        this.reader = reader;
        this.parser = parser;
    }

    @Override
    public List<Currency> getCurrencies() throws IOException, ParserConfigurationException, SAXException {
        if (!isCacheValid()) refreshCache();
        return currencies;
    }

    @Override
    public Currency getCurrencyByCode(CurrencyType code) throws IOException, ParserConfigurationException, SAXException {
        if (!isCacheValid()) refreshCache();
        if (code == CurrencyType.TRY) return TRY;
        return currencies.stream()
                .filter(c -> c.getCode().equals(code))
                .findFirst()
                .get(); // Exception will be added;
    }

    @Override
    public BigDecimal getExchangeRate(CurrencyType sourceType, CurrencyType destinationType) throws IOException, ParserConfigurationException, SAXException {
        if (sourceType == CurrencyType.TRY) {
            Currency currency = getCurrencyByCode(destinationType);
            return BigDecimal.ONE.divide(currency.getSellingRate(), RoundingMode.HALF_EVEN);
        } else if (destinationType == CurrencyType.TRY) {
            Currency currency = getCurrencyByCode(sourceType);
            return currency.getBuyingRate();
        } else {
            throw new RuntimeException(); // Exception will be modified
        }
    }

    private synchronized boolean isCacheValid() {
        return lastAccessTime != null && currencies != null && Duration.between(lastAccessTime, LocalDateTime.now()).toMinutes() < CACHE_DURATION.toMinutes();
    }

    private synchronized void refreshCache() throws IOException, ParserConfigurationException, SAXException {
        var parsedCurrencies = parser.parse(EXCHANGE_RATE_API.openStream());
        lastAccessTime = LocalDateTime.now();
        currencies = (List<Currency>) reader.read(parsedCurrencies);
    }
}
