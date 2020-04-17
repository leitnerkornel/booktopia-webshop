package com.codecool.shop.json;

public class CartEntryContainer {
    private String id;

    public String getId() {
        return this.id;
    }

    public int getIdAsNumber() {
        return Integer.parseInt(this.id);
    }
}
