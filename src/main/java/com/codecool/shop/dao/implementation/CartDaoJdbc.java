package com.codecool.shop.dao.implementation;

import com.codecool.shop.config.SQLConnection;
import com.codecool.shop.dao.CartDao;
import com.codecool.shop.dao.DataSourceException;
import com.codecool.shop.model.CartItem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CartDaoJdbc implements CartDao {
    private List<CartItem> data = new ArrayList<>();
    private static CartDaoJdbc instance = null;

    private CartDaoJdbc() {
    }

    public static CartDaoJdbc getInstance() {
        if (instance == null) {
            instance = new CartDaoJdbc();
        }
        return instance;
    }


    @Override
    public void add(CartItem item) throws DataSourceException {
        if (item == null) {
            throw new IllegalArgumentException("CartItem cannot be null");
        }
        Integer bookID = item.getBookID();
        String updateQuery = "UPDATE cart SET quantity = quantity + '1' WHERE book_id=?;";
        String insertQuery = "INSERT INTO cart (book_id) SELECT ? WHERE NOT EXISTS (SELECT ? FROM cart WHERE book_id = ?)";

        try (Connection cursor = SQLConnection.getDb();
             PreparedStatement prepUpdate =
                     cursor.prepareStatement(updateQuery,
                             ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE)
        ) {
            prepUpdate.setInt(1, bookID);
            prepUpdate.execute();

        } catch (SQLException e) {
            throw new DataSourceException(e);
        }

        try (Connection cursor = SQLConnection.getDb();
             PreparedStatement prepInsert =
                     cursor.prepareStatement(insertQuery,
                             ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE)
        ) {
            prepInsert.setInt(1, bookID);
            prepInsert.setInt(2, bookID);
            prepInsert.setInt(3, bookID);
            prepInsert.execute();

        } catch (SQLException e) {
            throw new DataSourceException(e);
        }
    }

    @Override
    public void removeItemById(int id) {

    }

    @Override
    public void clearCart() {

    }

    @Override
    public List<CartItem> getAll() {
        return null;
    }
}
