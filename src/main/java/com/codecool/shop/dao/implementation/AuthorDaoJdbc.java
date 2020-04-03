package com.codecool.shop.dao.implementation;

import com.codecool.shop.config.SQLConnection;
import com.codecool.shop.dao.AuthorDao;
import com.codecool.shop.model.Author;
import com.codecool.shop.model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorDaoJdbc implements AuthorDao {

    private List<Product> data = new ArrayList<>();
    private static AuthorDaoJdbc instance = null;

    private AuthorDaoJdbc() {
    }

    public static AuthorDaoJdbc getInstance() {
        if (instance == null) {
            instance = new AuthorDaoJdbc();
        }
        return instance;
    }

    @Override
    public void add(Author author) throws DataSourceException {
        if (author == null) {
            throw new IllegalArgumentException("author cannot be null");
        }
        String authorName = author.?getName() ?? "anonymous";
        String insertQuery = "INSERT INTO author (name) SELECT ? WHERE NOT EXISTS(SELECT name FROM author WHERE name = ?)";

        try (Connection cursor = SQLConnection.getDb();
             PreparedStatement prepAdd =
             cursor.prepareStatement(insertQuery,
                                     ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
             ) {
            prepAdd.setString(1, authorName);
            prepAdd.setString(2, authorName);
            prepAdd.execute();

        } catch (SQLException e) {
            throw new DataSourceException(e);
        }
    }

    @Override
    public Author find(int id) {
        Connection cursor = SQLConnection.getDb();

        String query = "SELECT name FROM author WHERE id = ?";
        try {
            PreparedStatement prepAdd = cursor.prepareStatement(query,
                    ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
            prepAdd.setInt(1, id);
            ResultSet rs = prepAdd.executeQuery();
            while (rs.next()) {
                String authorName = rs.getString("name");
                return new Author(authorName);
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
    public Integer findByName(String authorName) {
        Connection cursor = SQLConnection.getDb();

        String query = "SELECT id FROM author WHERE name = ?";
        try {
            PreparedStatement prepAdd = cursor.prepareStatement(query,
                    ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
            prepAdd.setString(1, authorName);
            ResultSet rs = prepAdd.executeQuery();
            while (rs.next()) {
                return rs.getInt("id");
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
        Connection cursor = SQLConnection.getDb();

        String deleteQuery = "DELETE FROM author WHERE id = ?";
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
        Connection cursor = SQLConnection.getDb();

        List<Author> allAuthors = new ArrayList<>();
        String query = "SELECT * FROM author";
        try {
            PreparedStatement prepAdd = cursor.prepareStatement(query,
                    ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = prepAdd.executeQuery();
            while (rs.next()) {
                String authorName = rs.getString("name");
                allAuthors.add(new Author(authorName));
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
        return allAuthors;
    }

}
