package com.demirelenes.bankingapp.currency.service.reader;

import com.demirelenes.bankingapp.currency.model.Currency;
import com.demirelenes.bankingapp.currency.model.CurrencyType;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class XMLCurrencyReader implements IReader<Currency> {

    private static final String CURRENCY = "Currency";
    private static final String CURRENCY_CODE = "CurrencyCode";
    private static final String CURRENCY_NAME = "CurrencyName";
    private static final String BUYING_RATE = "ForexBuying";
    private static final String SELLING_RATE = "ForexSelling";

    @Override
    public List<Currency> read(Document doc) {
        NodeList nodeList = doc.getElementsByTagName(CURRENCY);
        List<Currency> currencies = new ArrayList<>();

        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                Currency currency = parseCurrency(element);
                if (currency != null) currencies.add(parseCurrency(element));
            }
        }
        return currencies;
    }

    private Currency parseCurrency(Element element) {
        Currency currency = new Currency();
        if (!isValidCurrency(element.getAttribute(CURRENCY_CODE))) return null;
        currency.setCode(CurrencyType.valueOf(element.getAttribute(CURRENCY_CODE)));
        currency.setName(element.getElementsByTagName(CURRENCY_NAME).item(0).getTextContent());
        BigDecimal buyingRate = new BigDecimal(element.getElementsByTagName(BUYING_RATE).item(0).getTextContent());
        BigDecimal sellingRate = new BigDecimal(element.getElementsByTagName(SELLING_RATE).item(0).getTextContent());
        currency.setBuyingRate(buyingRate);
        currency.setSellingRate(sellingRate);
        return currency;
    }

    private boolean isValidCurrency(String code) {
        for (CurrencyType type : CurrencyType.values()) {
            if (type.getCode().equals(code)) {
                return true;
            }
        }
        return false;
    }
}
