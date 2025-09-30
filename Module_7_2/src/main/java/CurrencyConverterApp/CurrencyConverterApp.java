package CurrencyConverterApp;

import CurrencyConverterApp.controller.CurrencyConverterController;
import CurrencyConverterApp.view.CurrencyConverterView;
import javafx.application.Application;
import javafx.stage.Stage;

public class CurrencyConverterApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            CurrencyConverterView view = new CurrencyConverterView(primaryStage);
            CurrencyConverterController controller = new CurrencyConverterController();

            controller.setView(view);

            view.show();

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Failed to start Currency Converter application: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}