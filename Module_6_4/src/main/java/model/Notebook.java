package model;

import java.util.ArrayList;
import java.util.List;

public class Notebook {
    private List<Note> notes;

    // Constructor
    public Notebook() {
        this.notes = new ArrayList<>();
    }

    // Add a note to the notebook
    public void addNote(Note note) {
        if (note != null) {
            notes.add(note);
        }
    }

    // Get all notes
    public List<Note> getNotes() {
        return new ArrayList<>(notes); // Return a copy to prevent external modification
    }

    // Get a specific note by index
    public Note getNote(int index) {
        if (index >= 0 && index < notes.size()) {
            return notes.get(index);
        }
        return null;
    }

    // Remove a note by index
    public boolean removeNote(int index) {
        if (index >= 0 && index < notes.size()) {
            notes.remove(index);
            return true;
        }
        return false;
    }

    // Update a note at specific index
    public boolean updateNote(int index, Note updatedNote) {
        if (index >= 0 && index < notes.size() && updatedNote != null) {
            notes.set(index, updatedNote);
            return true;
        }
        return false;
    }

    // Get the number of notes
    public int size() {
        return notes.size();
    }

    // Check if notebook is empty
    public boolean isEmpty() {
        return notes.isEmpty();
    }

    // Get all notes as formatted text
    public String getFormattedNotes() {
        StringBuilder sb = new StringBuilder();
        if (notes.isEmpty()) {
            sb.append("No notes available.\n");
        } else {
            for (int i = 0; i < notes.size(); i++) {
                sb.append("Note ").append(i + 1).append(":\n");
                sb.append(notes.get(i).getFormattedNote()).append("\n");
            }
        }
        return sb.toString();
    }
}