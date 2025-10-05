package CurrencyConverterWithET.controller;

import CurrencyConverterWithET.dao.CurrencyDao;
import CurrencyConverterWithET.dao.TransactionDao;
import CurrencyConverterWithET.entity.Currency;
import CurrencyConverterWithET.entity.Transaction;
import CurrencyConverterWithET.view.AddCurrencyView;
import CurrencyConverterWithET.view.CurrencyConverterView;

import java.text.DecimalFormat;
import java.util.List;

public class CurrencyConverterController {
    private CurrencyDao currencyDao;
    private TransactionDao transactionDao;  // NEW
    private CurrencyConverterView view;
    private DecimalFormat decimalFormat;

    public CurrencyConverterController() {
        this.currencyDao = new CurrencyDao();
        this.transactionDao = new TransactionDao();  // NEW
        this.decimalFormat = new DecimalFormat("#,##0.00");
    }

    public void setView(CurrencyConverterView view) {
        this.view = view;
        view.setController(this);
        loadCurrenciesFromDatabase();
    }

    private void loadCurrenciesFromDatabase() {
        try {
            if (!currencyDao.testConnection()) {
                view.showError("Cannot connect to database. Please check your database connection.");
                return;
            }

            List<Currency> currencies = currencyDao.getAllCurrencies();

            if (currencies.isEmpty()) {
                view.showError("No currencies found in database. Please add currencies.");
                return;
            }

            view.populateCurrencyBoxes(currencies);
            view.showInfo("Successfully loaded " + currencies.size() + " currencies from database.");

        } catch (Exception e) {
            view.showError("Database error: " + e.getMessage());
            System.err.println("Failed to load currencies: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void handleConvertButton() {
        try {
            String amountText = view.getAmountText().trim();
            Currency fromCurrency = view.getFromCurrency();
            Currency toCurrency = view.getToCurrency();

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

            // Fetch exchange rates using JPA
            double fromRate = currencyDao.getExchangeRate(fromCurrency.getAbbreviation());
            double toRate = currencyDao.getExchangeRate(toCurrency.getAbbreviation());

            if (fromRate <= 0 || toRate <= 0) {
                view.showError("Exchange rate not found for selected currency.");
                return;
            }

            // Perform conversion
            double amountInUSD = amount / fromRate;
            double convertedAmount = amountInUSD * toRate;

            // Calculate effective exchange rate
            double effectiveRate = convertedAmount / amount;

            // Format and display result
            String formattedResult = decimalFormat.format(convertedAmount);
            view.setResult(formattedResult);

            // Point 4: Create and store transaction in database
            Transaction transaction = new Transaction(
                    fromCurrency,
                    toCurrency,
                    amount,
                    convertedAmount,
                    effectiveRate
            );

            // Point 4: Call DAO method to store transaction
            boolean saved = transactionDao.saveTransaction(transaction);

            if (saved) {
                System.out.println("Transaction stored successfully with ID: " +
                        transaction.getTransactionId());
            } else {
                System.err.println("Failed to store transaction in database.");
            }

        } catch (NumberFormatException e) {
            view.showError("Please enter a valid number.");
        } catch (Exception e) {
            view.showError("Error during conversion: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void handleAddCurrencyButton() {
        try {
            AddCurrencyView addView = new AddCurrencyView(view.getStage());

            addView.setOnCurrencyAdded(currency -> {
                boolean success = currencyDao.insertCurrency(currency);

                if (success) {
                    view.showInfo("Currency " + currency.getAbbreviation() + " added successfully!");
                } else {
                    view.showError("Failed to add currency. It may already exist.");
                }
            });

            addView.showAndWait();
            loadCurrenciesFromDatabase();

        } catch (Exception e) {
            view.showError("Error opening add currency window: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void refreshCurrencies() {
        loadCurrenciesFromDatabase();
    }
}
