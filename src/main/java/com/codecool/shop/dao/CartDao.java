package com.codecool.shop.dao;

import com.codecool.shop.model.CartItem;
import sun.jvm.hotspot.debugger.DataSource;

import java.util.List;

public interface CartDao {

    void add(CartItem item) throws DataSourceException;
    int itemsInCart() throws DataSourceException;
    void removeItemById(int id) throws DataSourceException;
    int getCartIdByBookId(int id) throws DataSourceException;
    void clearCart();

    List<CartItem> getAll() throws DataSourceException;
}
