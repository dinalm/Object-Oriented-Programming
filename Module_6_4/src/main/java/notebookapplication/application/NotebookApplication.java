package notebookapplication.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class NotebookApplication extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            // Load the FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/notebook.fxml"));
            Parent root = loader.load();

            // Create scene
            Scene scene = new Scene(root, 600, 500);

            // Configure primary stage
            primaryStage.setTitle("Notebook Application - JavaFX MVC");
            primaryStage.setScene(scene);
            primaryStage.setResizable(true);
            primaryStage.setMinWidth(500);
            primaryStage.setMinHeight(400);

            // Show the application
            primaryStage.show();

        } catch(Exception e) {
            e.printStackTrace();
            System.err.println("Error loading FXML file: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
