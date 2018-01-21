package uk.co.nisanci;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class SentenceParserTest {

    @Test
    public void nullInputInLowercaseAfterConstructor() {
        SentenceParser sentenceParser = new SentenceParser(null);
        assertNull(sentenceParser.getInput());
    }

    @Test
    public void emptyInputInLowercaseAfterConstructor() {
        SentenceParser sentenceParser = new SentenceParser("");
        assertEquals("", sentenceParser.getInput());
    }

    @Test
    public void emptySpaceInputInLowercaseAfterConstructor() {
        SentenceParser sentenceParser = new SentenceParser(" ");
        assertEquals(" ", sentenceParser.getInput());
    }

    @Test
    public void inputInLowercaseAfterConstructor() {
        SentenceParser sentenceParser = new SentenceParser("ABC.");
        assertEquals("abc.", sentenceParser.getInput());
    }

    @Test
    public void cleanNonAlphabeticalCharsForOnlyNonAlphaCharInput() {
        SentenceParser sentenceParser = new SentenceParser("..?,.");
        sentenceParser.replaceNonAlphabeticalCharsWithSpace();
        assertEquals(" ", sentenceParser.getInput());
    }

    @Test
    public void cleanNonAlphabeticalCharsForOneWordInput() {
        SentenceParser sentenceParser = new SentenceParser("ABC,.");
        sentenceParser.replaceNonAlphabeticalCharsWithSpace();
        assertEquals("abc ", sentenceParser.getInput());
    }

    @Test
    public void cleanNonAlphabeticalCharsWithTwoWordsInput() {
        SentenceParser sentenceParser = new SentenceParser("ABC DEF");
        sentenceParser.replaceNonAlphabeticalCharsWithSpace();
        assertEquals("abc def", sentenceParser.getInput());
    }

    @Test
    public void cleanNonAlphabeticalCharsForTwoWordsInputWithNonAlphaCharEnding() {
        SentenceParser sentenceParser = new SentenceParser("ABC,.a.");
        sentenceParser.replaceNonAlphabeticalCharsWithSpace();
        assertEquals("abc a ", sentenceParser.getInput());
    }

    @Test
    public void splitWordsForTwoWordsInput() {
        Map<String, Integer> expected = new HashMap<>();
        expected.put("abc", 1);
        expected.put("a", 1);

        SentenceParser sentenceParser = new SentenceParser("ABC a");
        sentenceParser.splitWordsBySpace();
        assertThat(sentenceParser.getWordMap(), is(expected));
    }

    @Test
    public void splitWordsForThreeWordsInput() {
        Map<String, Integer> expected = new HashMap<>();
        expected.put("abc", 2);
        expected.put("a", 1);

        SentenceParser sentenceParser = new SentenceParser("ABC a aBc");
        sentenceParser.splitWordsBySpace();
        assertThat(sentenceParser.getWordMap(), is(expected));
    }

    @Test
    public void prepareWordFrequenciesForEmptyWord() {
        Map<String, Integer> expected = new HashMap<>();

        SentenceParser sentenceParser = new SentenceParser("");
        sentenceParser.prepareWordFrequencies();

        assertThat(sentenceParser.getWordMap(), is(expected));
    }

    @Test
    public void prepareWordFrequenciesForOneWord() {
        Map<String, Integer> expected = new HashMap<>();
        expected.put("abc", 1);

        SentenceParser sentenceParser = new SentenceParser("abc");
        sentenceParser.prepareWordFrequencies();

        assertThat(sentenceParser.getWordMap(), is(expected));
    }

    @Test
    public void prepareWordFrequenciesForTwoWords() {
        Map<String, Integer> expected = new HashMap<>();
        expected.put("abc", 2);
        expected.put("def", 1);

        SentenceParser sentenceParser = new SentenceParser("ABC, DEF.abc");
        sentenceParser.prepareWordFrequencies();

        assertThat(sentenceParser.getWordMap(), is(expected));
    }

    @Test
    public void prepareWordFrequenciesForSampleInTest() {
        Map<String, Integer> expected = new HashMap<>();
        expected.put("this", 2);
        expected.put("is", 2);
        expected.put("a", 1);
        expected.put("statement", 1);
        expected.put("and", 1);
        expected.put("so", 1);

        SentenceParser sentenceParser = new SentenceParser("This is a statement, and so is this.");
        sentenceParser.prepareWordFrequencies();

        assertThat(sentenceParser.getWordMap(), is(expected));
    }
}
