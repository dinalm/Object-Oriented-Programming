package notebookapplication.model;

public class Note {
    private String title;
    private String content;

    // Default constructor
    public Note() {
        this.title = "";
        this.content = "";
    }

    // Constructor with parameters
    public Note(String title, String content) {
        this.title = title;
        this.content = content;
    }

    // Getter for title
    public String getTitle() {
        return title;
    }

    // Setter for title
    public void setTitle(String title) {
        this.title = title;
    }

    // Getter for content
    public String getContent() {
        return content;
    }

    // Setter for content
    public void setContent(String content) {
        this.content = content;
    }

    // Override toString for display purposes
    @Override
    public String toString() {
        return title + (title.isEmpty() ? "Untitled" : "");
    }

    // Method to get formatted note for display
    public String getFormattedNote() {
        return "Title: " + (title.isEmpty() ? "Untitled" : title) +
                "\nContent: " + content + "\n" + "-".repeat(30) + "\n";
    }
}
