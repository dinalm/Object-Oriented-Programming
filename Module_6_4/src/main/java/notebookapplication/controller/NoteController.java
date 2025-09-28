package notebookapplication.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import notebookapplication.model.Note;
import notebookapplication.model.Notebook;

import java.net.URL;
import java.util.ResourceBundle;

public class NoteController implements Initializable {

    // FXML injected components
    @FXML
    private TextField titleField;

    @FXML
    private TextArea contentArea;

    @FXML
    private Button addButton;

    @FXML
    private TextArea displayArea;

    // Model
    private Notebook notebook;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Initialize the model
        notebook = new Notebook();

        // Update display
        updateDisplay();
    }


    //Handles the Add button click event
    @FXML
    private void handleAddNote() {
        String title = titleField.getText().trim();
        String content = contentArea.getText().trim();

        // Validate input
        if (title.isEmpty() && content.isEmpty()) {
            showAlert("Warning", "Please enter at least a title or content for the note.");
            return;
        }

        // Create and add new note
        Note newNote = new Note(title, content);
        notebook.addNote(newNote);

        // Clear input fields
        clearFields();

        // Update display
        updateDisplay();
    }


    //Updates the display area with all notes
    private void updateDisplay() {
        displayArea.setText(notebook.getFormattedNotes());
    }


    //Clears the input fields
    private void clearFields() {
        titleField.clear();
        contentArea.clear();
    }

    //Shows an alert dialog
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
