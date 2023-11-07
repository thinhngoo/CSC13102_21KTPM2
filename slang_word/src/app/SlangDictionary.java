package app;

import java.util.HashMap;

public class SlangDictionary {
    private HashMap<String, String> slangWords;

    public SlangDictionary() {
        slangWords = new HashMap<>();
    }

    public void addWord(String word, String meaning) {
        slangWords.put(word, meaning);
    }

    public String getMeaning(String word) {
        return slangWords.get(word);
    }
}