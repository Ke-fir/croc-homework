package ru.croc.javaschool.homework4.exercise2;

import java.util.*;

/**
 * Class of rating.
 */
public class RatingBuilder {
    private ArrayList<String> articleRecords = new ArrayList<>();
    private ArrayList<Author> authors = new ArrayList<>();

    public ArrayList<Author> getAuthors() {
        return authors;
    }

    /**
     * Fills author list with data in records.
     */
    private void fillAuthors(){
        var authorsTable = new HashMap<String, ArrayList<Article>>();
        for (var record : articleRecords){
            String[] columnsOfRecord = record.split("; ", 3);
            var authorName = columnsOfRecord[1];
            var articleName = columnsOfRecord[0];
            var articleText = columnsOfRecord[2];
            if (authorsTable.containsKey(authorName)){
                authorsTable.get(authorName).add(new Article(articleName, articleText));
            } else {
                authorsTable.put(authorName, new ArrayList<>(Arrays.asList(new Article(articleName, articleText))));
            }
        }
        for (var authorName : authorsTable.keySet()){
            this.authors.add(new Author(authorName, authorsTable.get(authorName)));
        }
    }

    /**
     * Builds rating of authors.
     *
     * @return sorted list of authors
     */
    public ArrayList<Author> buildRating() {
        var rating = new ArrayList<Author>();
        rating.addAll(this.authors);
        Collections.sort(rating);
        return rating;
    }

    /**
     * Constructor of rating.
     *
     * @param articleRecords list of strings "article name; author; text"
     */
    public RatingBuilder(ArrayList<String> articleRecords) {
        this.articleRecords = articleRecords;
        fillAuthors();
    }
}
