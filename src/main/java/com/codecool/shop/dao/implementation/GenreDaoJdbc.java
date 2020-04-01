package com.codecool.shop.dao.implementation;

import com.codecool.shop.config.SQLConnection;
import com.codecool.shop.dao.GenreDao;
import com.codecool.shop.model.Genre;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GenreDaoJdbc implements GenreDao {
    private static Connection cursor = SQLConnection.getDb();

    @Override
    public void add(Genre category) {
        String genreName = category.getName();
        String insertQuery = "INSERT INTO genre (name) SELECT ? WHERE NOT EXISTS(SELECT name FROM genre WHERE name = ?)";

        try {
            PreparedStatement prepAdd = cursor.prepareStatement(insertQuery,
                    ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
            prepAdd.setString(1,genreName);
            prepAdd.executeQuery();

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
        String query = "SELECT name FROM genre WHERE id = ?";
        try {
            PreparedStatement prepAdd = cursor.prepareStatement(query,
                    ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
            prepAdd.setInt(1, id);
            ResultSet rs = prepAdd.executeQuery(query);
            while (rs.next()) {
                String genreName = rs.getString("name");
                Genre returnGenre = new Genre(genreName);
                return returnGenre;
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
    public void remove(int genreId) {
        String deleteQuery = "DELETE FROM genre WHERE (SELECT id FROM genre WHERE id = ?)";
        try {
            PreparedStatement prepAdd = cursor.prepareStatement(deleteQuery,
                    ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
            prepAdd.setInt(1, genreId);
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
        List<Genre> allGenre = new ArrayList<>();
        String query = "SELECT * FROM genre";
        try {
            PreparedStatement prepAdd = cursor.prepareStatement(query,
                    ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = prepAdd.executeQuery(query);
            while (rs.next()) {
                String genreName = rs.getString("name");
                allGenre.add(new Genre(genreName));
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

        return allGenre;
    }
}

