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
    public int itemsInCart() throws DataSourceException {
        int itemsInCart = 0;
        Connection cursor = SQLConnection.getDb();

        String query = "SELECT SUM(quantity) AS items_in_cart FROM cart;";
        try (PreparedStatement prepQuery = cursor.prepareStatement(query,
                ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE)) {
            ResultSet rs = prepQuery.executeQuery();
            while (rs.next()) {
                itemsInCart = rs.getInt("items_in_cart");
            }
        } catch (SQLException e) {
            throw new DataSourceException(e);
        }
        return itemsInCart;
    }

    @Override
    public void removeItemById(int id) throws DataSourceException {
        String deleteQuery = "DELETE FROM cart\n" +
                "WHERE id = ?;";

        try (Connection cursor = SQLConnection.getDb();
             PreparedStatement prepUpdate =
                     cursor.prepareStatement(deleteQuery,
                             ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE)
        ) {
            prepUpdate.setInt(1, id);
            prepUpdate.execute();

        } catch (SQLException e) {
            throw new DataSourceException(e);
        }
    }

    @Override
    public int getCartIdByBookId(int id) throws DataSourceException {
        int cartId = 0;
        Connection cursor = SQLConnection.getDb();
        String query = "SELECT id AS cart_id FROM cart WHERE book_id = ?;";

        try (PreparedStatement prepQuery = cursor.prepareStatement(query,
                ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE)) {
            prepQuery.setInt(1, id);
            ResultSet rs = prepQuery.executeQuery();
            while (rs.next()) {
                cartId = rs.getInt("cart_id");
            }
        } catch (SQLException e) {
            throw new DataSourceException(e);
        }
        return cartId;
    }

    @Override
    public void clearCart() {

    }

    @Override
    public List<CartItem> getAll() throws DataSourceException {
        List<CartItem> allCartItem = new ArrayList<>();
        Connection cursor = SQLConnection.getDb();
        String query = "SELECT\n" +
                "c.id AS cart_id,\n" +
                "b.id AS book_id,\n" +
                "a.name AS author_name,\n" +
                "b.title AS title,\n" +
                "b.price AS price,\n" +
                "c.quantity AS quantity,\n" +
                "c.quantity * b.price AS sub_total\n" +
                "FROM cart c JOIN book b on c.book_id = b.id JOIN author a on b.author_id = a.id\n" +
                "ORDER BY c.id;";
        try (PreparedStatement prepQuery = cursor.prepareStatement(query,
                ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE)) {
            ResultSet rs = prepQuery.executeQuery();
            while (rs.next()) {
                Integer cID = rs.getInt("cart_id");
                Integer bID = rs.getInt("book_id");
                String author = rs.getString("author_name");
                String title = rs.getString("title");
                Double price = rs.getDouble("price");
                Integer quantity = rs.getInt("quantity");
                Double total = rs.getDouble("sub_total");
                allCartItem.add(new CartItem(cID, bID, author, title, price, quantity, total));
            }
        } catch (SQLException e) {
            throw new DataSourceException(e);
        }
        return allCartItem;
    }
}

