package com.codecool.shop.model;

public class CartItem {
    private Integer id;
    private Integer bookID;
    private String authorName;
    private String title;
    private Double price;
    private Integer quantity;
    private Double subtotal;

    public CartItem() {

    }

    public CartItem(Integer id, Integer bookID, String authorName, String title, Double price, Integer quantity, Double subtotal) {
        this.id = id;
        this.bookID = bookID;
        this.authorName = authorName;
        this.title = title;
        this.price = price;
        this.quantity = quantity;
        this.subtotal = subtotal;
    }

    public CartItem(Integer bookID) {
        this.bookID = bookID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBookID() {
        return bookID;
    }

    public void setBookID(Integer bookID) {
        this.bookID = bookID;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "id=" + id +
                ", bookID=" + bookID +
                ", authorName='" + authorName + '\'' +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", subtotal=" + subtotal +
                '}';
    }
}
