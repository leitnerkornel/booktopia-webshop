package com.codecool.shop.dao.implementation;

import com.codecool.shop.config.SQLConnection;
import com.codecool.shop.dao.AuthorDao;
import com.codecool.shop.model.Author;
import com.codecool.shop.model.Genre;
import com.codecool.shop.model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorDaoJdbc implements AuthorDao {
    private static Connection cursor = SQLConnection.getDb();

    public AuthorDaoJdbc() {
    }

    @Override
    public void add(Author author) {
        String authorName = author.getName();
        String insertQuery = "INSERT INTO author (name) SELECT ? WHERE NOT EXISTS(SELECT name FROM author WHERE name = ?)";

        try {
            PreparedStatement prepAdd = cursor.prepareStatement(insertQuery,
                    ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
            prepAdd.setString(1,authorName);
            prepAdd.setString(2,authorName);
            prepAdd.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                cursor.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Author find(int id) {
        String query = "SELECT name FROM author WHERE id = ?";
        try {
            PreparedStatement prepAdd = cursor.prepareStatement(query,
                    ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
            prepAdd.setInt(1, id);
            ResultSet rs = prepAdd.executeQuery(query);
            while (rs.next()) {
                String authorName = rs.getString("name");
                Author returnAuthor = new Author(authorName);
                return returnAuthor;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                cursor.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public void remove(int authorId) {
        String deleteQuery = "DELETE FROM author WHERE (SELECT id FROM author WHERE id = ?)";
        try {
            PreparedStatement prepAdd = cursor.prepareStatement(deleteQuery,
                    ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
            prepAdd.setInt(1, authorId);
            prepAdd.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                cursor.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Author> getAll() {
        return null;
    }
}
