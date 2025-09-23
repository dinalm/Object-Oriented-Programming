package org.example.converter;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.util.List;

public class CurrencyConverterView {
    private Stage primaryStage;
    private TextField amountField;
    private TextField resultField;
    private ComboBox<Currency> fromCurrencyBox;
    private ComboBox<Currency> toCurrencyBox;
    private Button convertButton;
    private Label errorLabel;
    private CurrencyConverterController controller;

    public CurrencyConverterView(Stage primaryStage) {
        this.primaryStage = primaryStage;
        initializeComponents();
        setupLayout();
        applyStyles();
    }

    public void setController(CurrencyConverterController controller) {
        this.controller = controller;
        setupEventHandlers();
    }

    private void initializeComponents() {
        amountField = new TextField();
        amountField.setPromptText("Enter amount");

        resultField = new TextField();
        resultField.setEditable(false);
        resultField.setPromptText("Converted amount");

        fromCurrencyBox = new ComboBox<>();
        fromCurrencyBox.setPromptText("Select source currency");

        toCurrencyBox = new ComboBox<>();
        toCurrencyBox.setPromptText("Select target currency");

        convertButton = new Button("Convert");
        convertButton.setPrefWidth(120);

        errorLabel = new Label();
        errorLabel.setStyle("-fx-text-fill: red;");
        errorLabel.setVisible(false);
    }

    private void setupLayout() {
        // Main container
        VBox mainContainer = new VBox(20);
        mainContainer.setPadding(new Insets(30));
        mainContainer.setAlignment(Pos.CENTER);
        mainContainer.setStyle("-fx-background-color: #f5f5f5;");

        // Title
        Label titleLabel = new Label("Currency Converter");
        titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #2c3e50;");

        // Instructions
        Label instructionsLabel = new Label("Enter an amount, select currencies, and click Convert");
        instructionsLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #7f8c8d;");

        // Input section
        VBox inputSection = createInputSection();

        // Conversion section
        VBox conversionSection = createConversionSection();

        // Button and result section
        VBox buttonSection = createButtonSection();

        mainContainer.getChildren().addAll(
                titleLabel, instructionsLabel, inputSection,
                conversionSection, buttonSection, errorLabel
        );

        Scene scene = new Scene(mainContainer, 450, 550);
        primaryStage.setTitle("Currency Converter");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
    }

    private VBox createInputSection() {
        VBox inputSection = new VBox(10);
        inputSection.setAlignment(Pos.CENTER);

        Label amountLabel = new Label("Amount to Convert:");
        amountLabel.setStyle("-fx-font-weight: bold;");

        amountField.setPrefWidth(300);
        amountField.setStyle("-fx-font-size: 14px; -fx-padding: 8px;");

        inputSection.getChildren().addAll(amountLabel, amountField);
        return inputSection;
    }

    private VBox createConversionSection() {
        VBox conversionSection = new VBox(15);
        conversionSection.setAlignment(Pos.CENTER);

        // From currency section
        VBox fromSection = new VBox(5);
        fromSection.setAlignment(Pos.CENTER);
        Label fromLabel = new Label("From Currency:");
        fromLabel.setStyle("-fx-font-weight: bold;");
        fromCurrencyBox.setPrefWidth(300);
        fromCurrencyBox.setStyle("-fx-font-size: 14px;");
        fromSection.getChildren().addAll(fromLabel, fromCurrencyBox);

        // To currency section
        VBox toSection = new VBox(5);
        toSection.setAlignment(Pos.CENTER);
        Label toLabel = new Label("To Currency:");
        toLabel.setStyle("-fx-font-weight: bold;");
        toCurrencyBox.setPrefWidth(300);
        toCurrencyBox.setStyle("-fx-font-size: 14px;");
        toSection.getChildren().addAll(toLabel, toCurrencyBox);

        conversionSection.getChildren().addAll(fromSection, toSection);
        return conversionSection;
    }

    private VBox createButtonSection() {
        VBox buttonSection = new VBox(15);
        buttonSection.setAlignment(Pos.CENTER);

        convertButton.setStyle(
                "-fx-font-size: 16px; -fx-font-weight: bold; " +
                        "-fx-background-color: #3498db; -fx-text-fill: white; " +
                        "-fx-background-radius: 5px; -fx-padding: 10px 20px;"
        );

        Label resultLabel = new Label("Converted Amount:");
        resultLabel.setStyle("-fx-font-weight: bold;");

        resultField.setPrefWidth(300);
        resultField.setStyle(
                "-fx-font-size: 14px; -fx-padding: 8px; " +
                        "-fx-background-color: #ecf0f1; -fx-border-color: #bdc3c7;"
        );

        buttonSection.getChildren().addAll(convertButton, resultLabel, resultField);
        return buttonSection;
    }

    private void applyStyles() {
        // Apply CSS styles for better readability
        String css = """
            .root {
                -fx-font-family: 'Segoe UI', 'Arial', sans-serif;
            }
            .text-field {
                -fx-border-color: #bdc3c7;
                -fx-border-radius: 3px;
                -fx-background-radius: 3px;
            }
            .text-field:focused {
                -fx-border-color: #3498db;
                -fx-border-width: 2px;
            }
            .combo-box {
                -fx-border-radius: 3px;
                -fx-background-radius: 3px;
            }
            .button:hover {
                -fx-background-color: #2980b9;
            }
            .button:pressed {
                -fx-background-color: #21618c;
            }
        """;

        primaryStage.getScene().getRoot().setStyle(css);
    }

    private void setupEventHandlers() {
        // Restrict amount field to numeric input only
        amountField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*(\\.\\d*)?")) {
                amountField.setText(oldValue);
            }
        });

        convertButton.setOnAction(e -> {
            if (controller != null) {
                controller.handleConvertButton();
            }
        });

        // Enable conversion on Enter key
        amountField.setOnAction(e -> {
            if (controller != null) {
                controller.handleConvertButton();
            }
        });
    }

    public void populateCurrencyBoxes(List<Currency> currencies) {
        fromCurrencyBox.getItems().clear();
        toCurrencyBox.getItems().clear();

        fromCurrencyBox.getItems().addAll(currencies);
        toCurrencyBox.getItems().addAll(currencies);

        // Set default selections
        if (!currencies.isEmpty()) {
            fromCurrencyBox.setValue(currencies.get(0)); // USD
            toCurrencyBox.setValue(currencies.get(1));   // EUR
        }
    }

    public String getAmountText() { return amountField.getText(); }
    public Currency getFromCurrency() { return fromCurrencyBox.getValue(); }
    public Currency getToCurrency() { return toCurrencyBox.getValue(); }

    public void setResult(String result) {
        resultField.setText(result);
        hideError();
    }

    public void showError(String message) {
        errorLabel.setText(message);
        errorLabel.setVisible(true);
        resultField.clear();
    }

    public void hideError() {
        errorLabel.setVisible(false);
    }

    public void show() {
        primaryStage.show();
    }
}
