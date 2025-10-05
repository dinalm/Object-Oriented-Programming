package CurrencyConverterWithET;

import CurrencyConverterWithET.controller.CurrencyConverterController;
import CurrencyConverterWithET.util.JpaUtil;
import CurrencyConverterWithET.view.CurrencyConverterView;
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

    @Override
    public void stop() {
        // Clean up JPA resources when application closes
        JpaUtil.closeEntityManagerFactory();
        System.out.println("Application stopped. Resources cleaned up.");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
