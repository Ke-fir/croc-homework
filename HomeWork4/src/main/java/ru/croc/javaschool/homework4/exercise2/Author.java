package ru.croc.javaschool.homework4.exercise2;

import java.util.ArrayList;

/**
 * Author.
 * Has name, articles and average frequency of using header words in articles.
 */
public class Author implements Comparable<Author> {
    private final String name;
    private ArrayList<Article> articles = new ArrayList<>();
    private double averageFrequency;

    public String getName() {
        return name;
    }

    public ArrayList<Article> getArticles() {
        return articles;
    }

    public double getAverageFrequency() {
        return averageFrequency;
    }

    public void addArticle(Article article) {
        if (!articles.contains(article)) {
            this.articles.add(article);
        } else {
            System.err.println("This article was already added to list " + article.getName());
        }
    }

    /**
     * Counts average frequency and sets this value to the frequency field.
     */
    private void countAverageFrequency() {
        for (var article : articles) {
            if (article != null) {
                averageFrequency += article.getFrequencyOfWordsFromName();
            } else {
                System.err.println("Null article was faced");
            }
        }
        averageFrequency /= articles.size();
    }

    /**
     * Override for easy sort.
     *
     * @param o the author to be compared.
     * @return 1 if this < o
     */
    @Override
    public int compareTo(Author o) {
        return Double.compare(this.averageFrequency, o.averageFrequency) * -1;
    }

    @Override
    public boolean equals(Object obj) {
        boolean res = this.name.equalsIgnoreCase(((Author)obj).getName());
        return res;
    }

    /**
     * Constructor of Author
     *
     * @param name     String name
     * @param articles list of articles
     */
    public Author(String name, ArrayList<Article> articles) {
        this.name = name;
        this.articles = articles;
        countAverageFrequency();
    }

}
