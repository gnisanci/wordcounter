package uk.co.nisanci;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class App {
    public static void main(String[] args) throws IOException {
        System.out.println("\nInput:");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String sentence = br.readLine();
        SentenceParser sentenceParser = new SentenceParser(sentence);
        System.out.println("\nOutput:");
        sentenceParser.prepareAndPrintWordFrequencies();
    }
}
