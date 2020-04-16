package com.codecool.shop.model;

public class CartItem {
    private Integer id;
    private Integer bookID;
    private Integer quantity;

    public CartItem(Integer id, Integer bookID, Integer quantity) {
        this.id = id;
        this.bookID = bookID;
        this.quantity = quantity;
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
}
