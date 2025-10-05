package CurrencyConverterWithET.view;

import CurrencyConverterWithET.controller.CurrencyConverterController;
import CurrencyConverterWithET.entity.Currency;
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
    private Button refreshButton;
    private Button addCurrencyButton;
    private Label statusLabel;
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

        refreshButton = new Button("Refresh");
        refreshButton.setPrefWidth(120);

        addCurrencyButton = new Button("Add Currency");
        addCurrencyButton.setPrefWidth(150);

        statusLabel = new Label();
        statusLabel.setWrapText(true);
        statusLabel.setVisible(false);
    }

    private void setupLayout() {
        VBox mainContainer = new VBox(20);
        mainContainer.setPadding(new Insets(30));
        mainContainer.setAlignment(Pos.CENTER);
        mainContainer.setStyle("-fx-background-color: #f5f5f5;");

        Label titleLabel = new Label("Currency Converter");
        titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #2c3e50;");

        Label instructionsLabel = new Label("JPA-Enabled Version - Enter amount, select currencies, and convert");
        instructionsLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: #7f8c8d;");

        VBox inputSection = createInputSection();
        VBox conversionSection = createConversionSection();
        VBox buttonSection = createButtonSection();

        mainContainer.getChildren().addAll(
                titleLabel, instructionsLabel, inputSection,
                conversionSection, buttonSection, statusLabel
        );

        Scene scene = new Scene(mainContainer, 450, 650);
        primaryStage.setTitle("Currency Converter - JPA Edition");
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

        VBox fromSection = new VBox(5);
        fromSection.setAlignment(Pos.CENTER);
        Label fromLabel = new Label("From Currency:");
        fromLabel.setStyle("-fx-font-weight: bold;");
        fromCurrencyBox.setPrefWidth(300);
        fromCurrencyBox.setStyle("-fx-font-size: 14px;");
        fromSection.getChildren().addAll(fromLabel, fromCurrencyBox);

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

        HBox mainButtonBox = new HBox(10);
        mainButtonBox.setAlignment(Pos.CENTER);

        convertButton.setStyle(
                "-fx-font-size: 16px; -fx-font-weight: bold; " +
                        "-fx-background-color: #3498db; -fx-text-fill: white; " +
                        "-fx-background-radius: 5px; -fx-padding: 10px 20px;"
        );

        refreshButton.setStyle(
                "-fx-font-size: 14px; -fx-font-weight: bold; " +
                        "-fx-background-color: #95a5a6; -fx-text-fill: white; " +
                        "-fx-background-radius: 5px; -fx-padding: 8px 16px;"
        );

        mainButtonBox.getChildren().addAll(convertButton, refreshButton);

        addCurrencyButton.setStyle(
                "-fx-font-size: 14px; -fx-font-weight: bold; " +
                        "-fx-background-color: #27ae60; -fx-text-fill: white; " +
                        "-fx-background-radius: 5px; -fx-padding: 10px 20px;"
        );

        Label resultLabel = new Label("Converted Amount:");
        resultLabel.setStyle("-fx-font-weight: bold;");

        resultField.setPrefWidth(300);
        resultField.setStyle(
                "-fx-font-size: 14px; -fx-padding: 8px; " +
                        "-fx-background-color: #ecf0f1; -fx-border-color: #bdc3c7;"
        );

        buttonSection.getChildren().addAll(mainButtonBox, addCurrencyButton, resultLabel, resultField);
        return buttonSection;
    }

    private void applyStyles() {
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
                -fx-opacity: 0.8;
            }
            .button:pressed {
                -fx-opacity: 0.6;
            }
        """;

        primaryStage.getScene().getRoot().setStyle(css);
    }

    private void setupEventHandlers() {
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

        refreshButton.setOnAction(e -> {
            if (controller != null) {
                controller.refreshCurrencies();
            }
        });

        addCurrencyButton.setOnAction(e -> {
            if (controller != null) {
                controller.handleAddCurrencyButton();
            }
        });

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

        if (!currencies.isEmpty()) {
            fromCurrencyBox.setValue(currencies.get(0));
            if (currencies.size() > 1) {
                toCurrencyBox.setValue(currencies.get(1));
            }
        }
    }

    public String getAmountText() {
        return amountField.getText();
    }

    public Currency getFromCurrency() {
        return fromCurrencyBox.getValue();
    }

    public Currency getToCurrency() {
        return toCurrencyBox.getValue();
    }

    public Stage getStage() {
        return primaryStage;
    }

    public void setResult(String result) {
        resultField.setText(result);
        hideStatus();
    }

    public void showError(String message) {
        statusLabel.setText("❌ " + message);
        statusLabel.setStyle("-fx-text-fill: #e74c3c; -fx-font-weight: bold;");
        statusLabel.setVisible(true);
        resultField.clear();
    }

    public void showInfo(String message) {
        statusLabel.setText("ℹ️ " + message);
        statusLabel.setStyle("-fx-text-fill: #27ae60; -fx-font-weight: bold;");
        statusLabel.setVisible(true);
    }

    public void hideStatus() {
        statusLabel.setVisible(false);
    }

    public void show() {
        primaryStage.show();
    }
}
