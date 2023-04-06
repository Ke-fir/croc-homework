package ru.croc.javaschool.homework4.exercise2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Test class for rating builder.
 */
public class RatingBuilderTest {

    /**
     * Test of building rating.
     */
    @Test
    public void buildRatingTest() {
        var articleNumeric = new ArrayList<Article>(Arrays.asList(new Article("1 2 3", "1 2 3 4 5"),
                new Article("1, 5", "1 2 3 4 5")));
        var articleLetters = new ArrayList<Article>(Arrays.asList(new Article("A b C", "a B c d e f"),
                new Article("O!", "O p r s t u f h...")));
        var platon = new Author("Platon", articleLetters);
        var evklid = new Author("Evklid", articleNumeric);
        var expectedRating = new ArrayList<>(Arrays.asList(evklid, platon));
        var testInput = new ArrayList<>(Arrays.asList("A b C; Platon; a B c d e f", "1 2 3; Evklid; 1 2 3 4 5",
                "O!; Platon; O p r s t u f h...", "1, 5; Evklid; 1 2 3 4 5"));
        var ratingBuilder = new RatingBuilder(testInput);
        ArrayList<Author> actualRating = ratingBuilder.buildRating();
        Assertions.assertArrayEquals(expectedRating.toArray(), actualRating.toArray());
    }

    /**
     * Test of filling authors from strings.
     */
    @Test
    public void fillAuthorsTest() {
        var testInput = new ArrayList<>(Arrays.asList("1 2 3; Evklid; 1 2 3 4 5",
                "O!; Platon; O p r s t u f h...", "1, 5; Evklid; 1 2 3 4 5", "A b C; Platon; a B c d e f"));
        var articleNumeric = new ArrayList<Article>(Arrays.asList(new Article("1 2 3", "1 2 3 4 5"),
                new Article("1, 5", "1 2 3 4 5")));
        var articleLetters = new ArrayList<Article>(Arrays.asList(new Article("A b C", "a B c d e f"),
                new Article("O!", "O p r s t u f h...")));
        var platon = new Author("Platon", articleLetters);
        var evklid = new Author("Evklid", articleNumeric);
        var expectedAuthorsList = new ArrayList<>(Arrays.asList(evklid, platon));
        var ratingBuilder = new RatingBuilder(testInput);
        var actualAuthorsList = ratingBuilder.getAuthors();
        Assertions.assertArrayEquals(expectedAuthorsList.toArray(), actualAuthorsList.toArray());
    }
}
