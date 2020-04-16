package com.codecool.shop.dao.implementation;

import com.codecool.shop.config.SQLConnection;
import com.codecool.shop.dao.CartDao;
import com.codecool.shop.dao.DataSourceException;
import com.codecool.shop.model.CartItem;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        String insertQuery = "INSERT INTO cart (book_id) VALUES (?)";

        try (Connection cursor = SQLConnection.getDb();
             PreparedStatement prepAdd =
                     cursor.prepareStatement(insertQuery,
                             ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE)
        ) {
            prepAdd.setInt(1, bookID);
            prepAdd.execute();

        } catch (SQLException e) {
            throw new DataSourceException(e);
        }
    }

    @Override
    public void removeItemById(int id) {

    }

    @Override
    public boolean isInCartAlready(Integer bookID) throws DataSourceException {
        return true;
    }
    /*
        String insertQuery = "INSERT INTO cart (book_id) VALUES (?)";

        try (Connection cursor = SQLConnection.getDb();
             PreparedStatement prepAdd =
                     cursor.prepareStatement(insertQuery,
                             ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
        ) {
            prepAdd.setInt(1, bookID);
            prepAdd.execute();

        } catch (SQLException e) {
            throw new DataSourceException(e);
        }
    }*/
    @Override
    public void clearCart() {

    }

    @Override
    public List<CartItem> getAll() {
        return null;
    }
}
