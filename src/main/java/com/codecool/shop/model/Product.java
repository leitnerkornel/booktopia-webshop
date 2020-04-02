package com.codecool.shop.model;

import java.util.Currency;

public class Product extends BaseModel {
    // for the template
    private Integer bookID;
    private String authorName;
    private String genreName;
    private String recommenderName;



    private float defaultPrice;
    private Currency defaultCurrency;
    private Integer genre;
    private Integer recommender;
    private Integer author;
    private Genre genreObj;
    private Author authorObj;
    private Integer stock;
    private Recommender recommenderObj;


    public Product(String name, float defaultPrice, String currencyString, String description, Integer genre, Integer recommender, Integer author) {
        super(name, description);
        this.setPrice(defaultPrice, currencyString);
        this.setRecommender(recommender);
        this.setGenre(genre);
        this.setAuthor(author);
    }

    public Product(String name, float defaultPrice, String currencyString, String description, Genre genre, Recommender recommender, Author author) {
        super(name, description);
        this.setPrice(defaultPrice, currencyString);
        this.setGenreObj(genre);
        this.setAuthorObj(author);
        this.setRecommenderObj(recommender);
    }

    public Integer getBookID() {
        return bookID;
    }

    public String getAuthorName() {
        return authorName;
    }

    public String getGenreName() {
        return genreName;
    }

    public String getRecommenderName() {
        return recommenderName;
    }

    public Author getAuthorObj() {
        return authorObj;
    }

    public Recommender getRecommenderObj() {
        return recommenderObj;
    }

    // Constructor for the template (ProductDaoJdbc.getAll method will use this)
    public Product(String name, String description, Integer bookID, float defaultPrice, Integer stock, Integer authorID, String authorName, Integer genreID, String genreName, Integer recommenderID, String recommenderName) {
        super(name, description); // Book title, book description
        this.bookID = bookID;
        this.setPrice(defaultPrice,"USD");
        this.setStock(stock);
        this.author = authorID;
        this.authorName = authorName;
        this.genre = genreID;
        this.genreName = genreName;
        this.recommender = recommenderID;
        this.recommenderName = recommenderName;
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

    public Genre getGenreObj() {
        return genreObj;
    }

    public void setGenreObj(Genre genre) {
        this.genreObj = genre;
        this.genreObj.addProduct(this);
    }

    public Integer getRecommender() {
        return recommender;
    }

    public void setRecommender(Integer recommender) {
        this.recommender = recommender;
    }

    public void setRecommenderObj(Recommender recommender) {
        this.recommenderObj = recommender;
        this.recommenderObj.addProduct(this);
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
                this.defaultCurrency.toString());
                //this.genre.getName(),
                //this.recommender.getName());
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getStock() {
        return stock;
    }
}
