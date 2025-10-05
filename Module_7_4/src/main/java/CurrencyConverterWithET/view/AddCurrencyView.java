package CurrencyConverterWithET.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import CurrencyConverterWithET.entity.Currency;

import java.util.function.Consumer;

public class AddCurrencyView {
    private Stage stage;
    private TextField abbreviationField;
    private TextField nameField;
    private TextField rateField;
    private Label errorLabel;
    private Consumer<Currency> onCurrencyAdded;

    public AddCurrencyView(Stage parentStage) {
        initializeStage(parentStage);
        initializeComponents();
        setupLayout();
        applyStyles();
    }

    private void initializeStage(Stage parentStage) {
        stage = new Stage();
        stage.setTitle("Add New Currency");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(parentStage);
        stage.setResizable(false);
    }

    private void initializeComponents() {
        abbreviationField = new TextField();
        abbreviationField.setPromptText("e.g., EUR");
        abbreviationField.setMaxWidth(200);

        nameField = new TextField();
        nameField.setPromptText("e.g., Euro");
        nameField.setMaxWidth(200);

        rateField = new TextField();
        rateField.setPromptText("e.g., 0.85");
        rateField.setMaxWidth(200);

        errorLabel = new Label();
        errorLabel.setStyle("-fx-text-fill: red; -fx-font-weight: bold;");
        errorLabel.setVisible(false);
        errorLabel.setWrapText(true);
        errorLabel.setMaxWidth(350);
    }

    private void setupLayout() {
        VBox mainContainer = new VBox(20);
        mainContainer.setPadding(new Insets(30));
        mainContainer.setAlignment(Pos.CENTER);
        mainContainer.setStyle("-fx-background-color: #f5f5f5;");

        Label titleLabel = new Label("Add New Currency");
        titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: #2c3e50;");

        // Form grid
        GridPane formGrid = new GridPane();
        formGrid.setHgap(10);
        formGrid.setVgap(15);
        formGrid.setAlignment(Pos.CENTER);

        Label abbrLabel = new Label("Abbreviation (3 letters):");
        abbrLabel.setStyle("-fx-font-weight: bold;");

        Label nameLabel = new Label("Currency Name:");
        nameLabel.setStyle("-fx-font-weight: bold;");

        Label rateLabel = new Label("Rate to USD:");
        rateLabel.setStyle("-fx-font-weight: bold;");

        formGrid.add(abbrLabel, 0, 0);
        formGrid.add(abbreviationField, 1, 0);
        formGrid.add(nameLabel, 0, 1);
        formGrid.add(nameField, 1, 1);
        formGrid.add(rateLabel, 0, 2);
        formGrid.add(rateField, 1, 2);

        // Buttons
        HBox buttonBox = new HBox(10);
        buttonBox.setAlignment(Pos.CENTER);

        Button addButton = new Button("Add Currency");
        addButton.setStyle(
                "-fx-font-size: 14px; -fx-font-weight: bold; " +
                        "-fx-background-color: #27ae60; -fx-text-fill: white; " +
                        "-fx-background-radius: 5px; -fx-padding: 10px 20px;"
        );
        addButton.setOnAction(e -> handleAddCurrency());

        Button cancelButton = new Button("Cancel");
        cancelButton.setStyle(
                "-fx-font-size: 14px; -fx-font-weight: bold; " +
                        "-fx-background-color: #95a5a6; -fx-text-fill: white; " +
                        "-fx-background-radius: 5px; -fx-padding: 10px 20px;"
        );
        cancelButton.setOnAction(e -> stage.close());

        buttonBox.getChildren().addAll(addButton, cancelButton);

        mainContainer.getChildren().addAll(titleLabel, formGrid, buttonBox, errorLabel);

        Scene scene = new Scene(mainContainer, 400, 350);
        stage.setScene(scene);
    }

    private void applyStyles() {
        String css = """
            .root {
                -fx-font-family: 'Segoe UI', 'Arial', sans-serif;
            }
            .text-field {
                -fx-font-size: 14px;
                -fx-padding: 8px;
                -fx-border-color: #bdc3c7;
                -fx-border-radius: 3px;
                -fx-background-radius: 3px;
            }
            .text-field:focused {
                -fx-border-color: #3498db;
                -fx-border-width: 2px;
            }
            .button:hover {
                -fx-opacity: 0.8;
            }
        """;
        stage.getScene().getRoot().setStyle(css);
    }

    private void handleAddCurrency() {
        String abbreviation = abbreviationField.getText().trim().toUpperCase();
        String name = nameField.getText().trim();
        String rateText = rateField.getText().trim();

        // Validation
        if (abbreviation.isEmpty() || name.isEmpty() || rateText.isEmpty()) {
            showError("All fields are required.");
            return;
        }

        if (abbreviation.length() != 3) {
            showError("Abbreviation must be exactly 3 letters.");
            return;
        }

        if (!abbreviation.matches("[A-Z]{3}")) {
            showError("Abbreviation must contain only letters.");
            return;
        }

        double rate;
        try {
            rate = Double.parseDouble(rateText);
            if (rate <= 0) {
                showError("Rate must be a positive number.");
                return;
            }
        } catch (NumberFormatException e) {
            showError("Rate must be a valid number.");
            return;
        }

        // Create currency object
        Currency currency = new Currency(abbreviation, name, rate);

        // Notify callback
        if (onCurrencyAdded != null) {
            onCurrencyAdded.accept(currency);
        }

        stage.close();
    }

    private void showError(String message) {
        errorLabel.setText("❌ " + message);
        errorLabel.setVisible(true);
    }

    public void setOnCurrencyAdded(Consumer<Currency> callback) {
        this.onCurrencyAdded = callback;
    }

    public void showAndWait() {
        stage.showAndWait();
    }
}
