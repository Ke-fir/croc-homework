package ru.croc.javaschool.homework4.exercise2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Test class for authors.
 */
public class AuthorTest {

    @Test
    public void countAverageFrequencyTest(){
        var articles = new ArrayList<Article>(Arrays.asList(new Article("1 2 3", "1 2 3 4 5"),
                new Article("1 5", "1 2 3 4 5")));
        var author = new Author("Platon", articles);
        var actualAverage = author.getAverageFrequency();
        Assertions.assertEquals(50, actualAverage);
    }
}
