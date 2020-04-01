package com.codecool.shop.dao.implementation;

import com.codecool.shop.config.SQLConnection;
import com.codecool.shop.dao.GenreDao;
import com.codecool.shop.model.Genre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class GenreDaoJdbc implements GenreDao {
    private static Connection cursor = SQLConnection.getDb();

    @Override
    public void add(Genre category) {
        String insertQuery = "INSERT INTO genre (name) VALUES (?)";

        try {
            PreparedStatement prepAdd = cursor.prepareStatement(insertQuery,
                    ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
            prepAdd.setString(1, category.getName());
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
    public Genre find(int id) {
        return null;
    }

    @Override
    public void remove(int genreId) {
        String deleteQuery = "DELETE FROM genre WHERE (SELECT id FROM genre WHERE id = ${genreId})" ;
        try {
            PreparedStatement prepAdd = cursor.prepareStatement(deleteQuery,
                    ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
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
    public List<Genre> getAll() {
        return null;
    }
}

