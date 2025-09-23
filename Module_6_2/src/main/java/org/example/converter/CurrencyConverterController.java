package org.example.converter;

import java.text.DecimalFormat;

public class CurrencyConverterController {
    private CurrencyModel model;
    private CurrencyConverterView view;
    private DecimalFormat decimalFormat;

    public CurrencyConverterController() {
        model = new CurrencyModel();
        decimalFormat = new DecimalFormat("#,##0.00");
    }

    public void setView(CurrencyConverterView view) {
        this.view = view;
        view.setController(this);
        view.populateCurrencyBoxes(model.getCurrencies());
    }

    public void handleConvertButton() {
        try {
            // Get input values
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

            // Perform conversion
            double convertedAmount = model.convertCurrency(amount, fromCurrency, toCurrency);

            // Format and display result
            String formattedResult = decimalFormat.format(convertedAmount);
            view.setResult(formattedResult);

        } catch (NumberFormatException e) {
            view.showError("Please enter a valid number.");
        } catch (IllegalArgumentException e) {
            view.showError("Error: " + e.getMessage());
        } catch (Exception e) {
            view.showError("An unexpected error occurred. Please try again.");
        }
    }
}
