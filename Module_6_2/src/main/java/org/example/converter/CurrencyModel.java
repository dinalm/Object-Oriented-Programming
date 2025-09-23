package org.example.converter;

import java.util.ArrayList;
import java.util.List;

public class CurrencyModel {
    private List<Currency> currencies;

    public CurrencyModel() {
        currencies = new ArrayList<>();
        initializeCurrencies();
    }

    private void initializeCurrencies() {
        // Hardcoded currencies with conversion rates to USD
        currencies.add(new Currency("USD", "US Dollar", 1.0));
        currencies.add(new Currency("EUR", "Euro", 0.85));
        currencies.add(new Currency("GBP", "British Pound", 0.73));
        currencies.add(new Currency("JPY", "Japanese Yen", 110.0));
        currencies.add(new Currency("CAD", "Canadian Dollar", 1.25));
        currencies.add(new Currency("AUD", "Australian Dollar", 1.35));
        currencies.add(new Currency("CHF", "Swiss Franc", 0.92));
        currencies.add(new Currency("CNY", "Chinese Yuan", 6.45));
        currencies.add(new Currency("INR", "Indian Rupee", 74.5));
        currencies.add(new Currency("KRW", "South Korean Won", 1180.0));
    }

    public List<Currency> getCurrencies() {
        return new ArrayList<>(currencies);
    }

    public Currency getCurrencyByAbbreviation(String abbreviation) {
        return currencies.stream()
                .filter(c -> c.getAbbreviation().equals(abbreviation))
                .findFirst()
                .orElse(null);
    }

    public double convertCurrency(double amount, Currency fromCurrency, Currency toCurrency) {
        if (fromCurrency == null || toCurrency == null) {
            throw new IllegalArgumentException("Invalid currency selection");
        }

        // Convert from source currency to USD, then from USD to target currency
        double amountInUSD = amount / fromCurrency.getRateToUSD();
        return amountInUSD * toCurrency.getRateToUSD();
    }
}

