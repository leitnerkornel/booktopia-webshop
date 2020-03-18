package com.codecool.shop.model;

import java.util.Currency;

public class Product extends BaseModel {

    private float defaultPrice;
    private Currency defaultCurrency;
    private Genre genre;
    private Recommender recommender;


    public Product(String name, float defaultPrice, String currencyString, String description, Genre genre, Recommender recommender) {
        super(name, description);
        this.setPrice(defaultPrice, currencyString);
        this.setRecommender(recommender);
        this.setGenre(genre);
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

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
        this.genre.addProduct(this);
    }

    public Recommender getRecommender() {
        return recommender;
    }

    public void setRecommender(Recommender recommender) {
        this.recommender = recommender;
        this.recommender.addProduct(this);
    }

    @Override
    public String toString() {
        return String.format("id: %1$d, " +
                        "name: %2$s, " +
                        "defaultPrice: %3$f, " +
                        "defaultCurrency: %4$s, " +
                        "productCategory: %5$s, " +
                        "supplier: %6$s",
                this.id,
                this.name,
                this.defaultPrice,
                this.defaultCurrency.toString(),
                this.genre.getName(),
                this.recommender.getName());
    }
}
