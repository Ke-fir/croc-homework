package ru.croc.javaschool.homework4.exercise2;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Article.
 * Have name, text and frequency of using words from article in text.
 */
public class Article {
    private String name;
    private String text;
    /**
     * Frequency in %s that is calculated by formula: (count of article words in text) / (count of words in text) * 100
     */
    private double frequencyOfWordsFromName;

    public String getName() {
        return name;
    }

    public String getText() {
        return text;
    }

    public double getFrequencyOfWordsFromName() {
        return frequencyOfWordsFromName;
    }

    private void countFrequency() {
        int wordsFromNameCount = 0;
        String[] wordsFromName = this.name.split("[\\p{Punct}\\s]+");
        for (var word : wordsFromName) {
            Pattern regex = Pattern.compile("\\b" + word + "\\b", Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);
            Matcher matcher = regex.matcher(this.text);
            while (matcher.find()) {
                wordsFromNameCount++;
            }
        }
        double countOfAllWords = this.text.split("[\\p{Punct}\\s]+").length;
        this.frequencyOfWordsFromName = (wordsFromNameCount / countOfAllWords) * 100;
    }

    public Article(String name, String text) {
        this.name = name;
        this.text = text;
        countFrequency();
    }
}
