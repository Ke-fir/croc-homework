package ru.croc.javaschool.homework4.exercise2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Test class for articles.
 */
public class ArticleTest {


    /**
     * Test of frequency counting.
     */
    @Test
    public void countFrequencyTest(){
        /* .countFrequency is used in constructor */
        var article = new Article("The 1st article.", "This is the first article of our the greatest " +
                "articles magazine ever.");
        Assertions.assertEquals(25, article.getFrequencyOfWordsFromName());
    }
}
