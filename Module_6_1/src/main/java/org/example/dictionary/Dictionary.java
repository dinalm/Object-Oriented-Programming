package org.example.dictionary;

import java.util.HashMap;

class WordNotFoundException extends Exception {
    public WordNotFoundException(String message) {
        super(message);
    }
}

public class Dictionary {
    private HashMap<String, String> words;

    public Dictionary() {
        this.words = new HashMap<>();
    }

    public void addWord(String word, String meaning) {
        words.put(word.toLowerCase(), meaning);
    }

    public String searchWord(String word) throws WordNotFoundException {
        String meaning = words.get(word.toLowerCase());
        if (meaning == null) {
            throw new WordNotFoundException("Word '" + word + "' not found in dictionary.");
        }
        return meaning;
    }
}
