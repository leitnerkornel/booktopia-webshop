package com.codecool.shop.dao;

import com.codecool.shop.model.CartItem;
import sun.jvm.hotspot.debugger.DataSource;

import java.util.List;

public interface CartDao {

    void add(CartItem item) throws DataSourceException;
    void removeItemById(int id);
    boolean isInCartAlready(Integer bookID) throws DataSourceException;

    void clearCart();

    List<CartItem> getAll();
}
