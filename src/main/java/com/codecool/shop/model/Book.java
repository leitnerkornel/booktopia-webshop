package com.codecool.shop.model;

public class Book extends Product {
    public Book(String name, float defaultPrice, String currencyString, String description, Genre genre, Recommender recommender, Author author) {
        super(name, defaultPrice, currencyString, description, genre, recommender, author);
    }
}
