package CurrencyConverterApp.controller;

import CurrencyConverterApp.dao.CurrencyDao;
import CurrencyConverterApp.entity.Currency;
import CurrencyConverterApp.view.CurrencyConverterView;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.List;

public class CurrencyConverterController {
    private CurrencyDao currencyDao;
    private CurrencyConverterView view;
    private DecimalFormat decimalFormat;

    public CurrencyConverterController() {
        this.currencyDao = new CurrencyDao();
        this.decimalFormat = new DecimalFormat("#,##0.00");
    }

    public void setView(CurrencyConverterView view) {
        this.view = view;
        view.setController(this);
        loadCurrenciesFromDatabase();
    }

    private void loadCurrenciesFromDatabase() {
        try {
            // Test database connection first
            if (!currencyDao.testConnection()) {
                view.showError("Cannot connect to database. Please check your database connection.");
                return;
            }

            List<Currency> currencies = currencyDao.getAllCurrencies();

            if (currencies.isEmpty()) {
                view.showError("No currencies found in database. Please populate the database.");
                return;
            }

            view.populateCurrencyBoxes(currencies);
            view.showInfo("Successfully loaded " + currencies.size() + " currencies from database.");

        } catch (SQLException e) {
            view.showError("Database error: " + e.getMessage());
            System.err.println("Failed to load currencies: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void handleConvertButton() {
        try {
            // Get input values from CurrencyConverterApp.view
            String amountText = view.getAmountText().trim();
            Currency fromCurrency = view.getFromCurrency();
            Currency toCurrency = view.getToCurrency();

            // Validate input
            if (amountText.isEmpty()) {
                view.showError("Please enter an amount to convert.");
                return;
            }

            if (fromCurrency == null) {
                view.showError("Please select a source currency.");
                return;
            }

            if (toCurrency == null) {
                view.showError("Please select a target currency.");
                return;
            }

            double amount = Double.parseDouble(amountText);

            if (amount < 0) {
                view.showError("Amount cannot be negative.");
                return;
            }

            if (amount == 0) {
                view.setResult("0.00");
                return;
            }

            // Fetch exchange rates from database
            double fromRate = currencyDao.getExchangeRate(fromCurrency.getAbbreviation());
            double toRate = currencyDao.getExchangeRate(toCurrency.getAbbreviation());

            // Validate rates
            if (fromRate <= 0 || toRate <= 0) {
                view.showError("Exchange rate not found for selected currency.");
                return;
            }

            // Perform conversion: amount -> USD -> target currency
            double amountInUSD = amount / fromRate;
            double convertedAmount = amountInUSD * toRate;

            // Format and display result
            String formattedResult = decimalFormat.format(convertedAmount);
            view.setResult(formattedResult);

        } catch (NumberFormatException e) {
            view.showError("Please enter a valid number.");
        } catch (SQLException e) {
            view.showError("Database error: Unable to fetch exchange rates. " + e.getMessage());
            System.err.println("Database error during conversion: " + e.getMessage());
        } catch (Exception e) {
            view.showError("An unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void refreshCurrencies() {
        loadCurrenciesFromDatabase();
    }
}
