package org.example.converter;

import javafx.application.Application;
import javafx.stage.Stage;

public class CurrencyConverterApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create MVC components
        CurrencyConverterView view = new CurrencyConverterView(primaryStage);
        CurrencyConverterController controller = new CurrencyConverterController();

        // Connect view and controller
        controller.setView(view);

        // Show the application
        view.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}