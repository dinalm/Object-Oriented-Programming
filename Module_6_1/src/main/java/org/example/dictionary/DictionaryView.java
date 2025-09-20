package org.example.dictionary;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class DictionaryView extends Application {
    private TextField wordInput;
    private Button searchButton;
    private TextArea meaningDisplay;
    private DictionaryController controller;

    @Override
    public void start(Stage primaryStage) {
        controller = new DictionaryController();

        wordInput = new TextField();
        wordInput.setPromptText("Enter a word to search");
        wordInput.setPrefWidth(200);

        searchButton = new Button("Search");
        searchButton.setOnAction(e -> handleSearch());

        meaningDisplay = new TextArea();
        meaningDisplay.setEditable(false);
        meaningDisplay.setPrefSize(300, 100);
        meaningDisplay.setWrapText(true);

        FlowPane root = new FlowPane();
        root.setPadding(new Insets(10));
        root.setHgap(10);
        root.setVgap(10);
        root.getChildren().addAll(wordInput, searchButton, meaningDisplay);

        primaryStage.setTitle("Virtual Dictionary");
        primaryStage.setScene(new Scene(root, 400, 200));
        primaryStage.show();
    }

    private void handleSearch() {
        String word = wordInput.getText().trim();

        if (word.isEmpty()) {
            meaningDisplay.setText("Please enter a word to search.");
            return;
        }

        String result = controller.searchWord(word);
        meaningDisplay.setText(result);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
