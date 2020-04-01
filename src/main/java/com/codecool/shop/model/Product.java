package com.codecool.shop.model;

import java.util.Currency;

public class Product extends BaseModel {

    private float defaultPrice;
    private Currency defaultCurrency;
    private Integer genre;
    private Recommender recommender;
    private Integer author;
    private Genre genreObj;
    private Author authorObj;


    public Product(String name, float defaultPrice, String currencyString, String description, Integer genre, Recommender recommender, Integer author) {
        super(name, description);
        this.setPrice(defaultPrice, currencyString);
        this.setRecommender(recommender);
        this.setGenre(genre);
        this.setAuthor(author);
    }

    public Product(String name, float defaultPrice, String currencyString, String description, Genre genre, Recommender recommender, Author author) {
        super(name, description);
        this.setPrice(defaultPrice, currencyString);
        this.setRecommender(recommender);
        this.setGenreObj(genre);
        this.setAuthorObj(author);
    }

    public float getDefaultPrice() {
        return defaultPrice;
    }

    public void setDefaultPrice(float defaultPrice) {
        this.defaultPrice = defaultPrice;
    }

    public Currency getDefaultCurrency() {
        return defaultCurrency;
    }

    public void setDefaultCurrency(Currency defaultCurrency) {
        this.defaultCurrency = defaultCurrency;
    }

    public String getPrice() {
        return String.valueOf(this.defaultPrice) + " " + this.defaultCurrency.toString();
    }

    public void setPrice(float price, String currency) {
        this.defaultPrice = price;
        this.defaultCurrency = Currency.getInstance(currency);
    }

    public Integer getGenre() {
        return genre;
    }

    public void setGenre(Integer genre) {
        this.genre = genre;
    }

    public void setGenreObj(Genre genre) {
        this.genreObj = genre;
        this.genreObj.addProduct(this);
    }

    public Recommender getRecommender() {
        return recommender;
    }

    public void setRecommender(Recommender recommender) {
        this.recommender = recommender;
        this.recommender.addProduct(this);
    }

    public Integer getAuthor() { return author;}

    public void setAuthor(Integer author) {
        this.author = author;
    }

    public void setAuthorObj(Author author) {
        this.authorObj = author;
        this.authorObj.addProduct(this);
    }

    @Override
    public String toString() {
        return String.format("id: %1$d, " +
                        "name: %2$s, " +
                        "defaultPrice: %3$f, " +
                        "defaultCurrency: %4$s, " +
                        //"productCategory: %5$s, " +
                        "supplier: %6$s",
                this.id,
                this.name,
                this.defaultPrice,
                this.defaultCurrency.toString(),
                //this.genre.getName(),
                this.recommender.getName());
    }
}
