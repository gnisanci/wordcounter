package uk.co.nisanci;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class SentenceParser {

    private String input;
    private HashMap<String, Integer> wordMap = new LinkedHashMap<>();

    public SentenceParser(String input) {
        if (input != null)
            this.input = input.toLowerCase();
    }

    public void prepareAndPrintWordFrequencies() {
        prepareWordFrequencies();

        for (String word : wordMap.keySet()) {
            System.out.println(word + " - " + wordMap.get(word));
        }
    }

    void prepareWordFrequencies() {
        replaceNonAlphabeticalCharsWithSpace();
        splitWordsBySpace();
    }

    void replaceNonAlphabeticalCharsWithSpace() {
        input = input.replaceAll("[^A-Za-z]+", " ");
    }

    void splitWordsBySpace() {
        String[] words = input.split("\\s+");
        int frequency;
        for (String word : words) {
            if (wordMap.containsKey(word))
                frequency = wordMap.get(word) + 1;
            else
                frequency = 1;

            if (word.length() > 0)
                wordMap.put(word, frequency);
        }
    }

    HashMap getWordMap() {
        return wordMap;
    }

    String getInput() {
        return input;
    }
}
