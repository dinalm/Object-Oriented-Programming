package org.example.dictionary;

public class DictionaryController {
    private Dictionary dictionary;

    public DictionaryController() {
        this.dictionary = new Dictionary();

        // Populate dictionary with some words
        dictionary.addWord("apple", "A round fruit with red or green skin");
        dictionary.addWord("book", "A written or printed work consisting of pages");
        dictionary.addWord("computer", "An electronic device for storing and processing data");
        dictionary.addWord("dog", "A domesticated carnivorous mammal");
        dictionary.addWord("elephant", "A large mammal with a trunk");
    }

    public String searchWord(String word) {
        try {
            return dictionary.searchWord(word);
        } catch (WordNotFoundException e) {
            return e.getMessage();
        }
    }
}
