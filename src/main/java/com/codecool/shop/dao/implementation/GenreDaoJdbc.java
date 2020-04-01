package com.codecool.shop.dao.implementation;

import com.codecool.shop.config.SQLConnection;
import com.codecool.shop.dao.GenreDao;
import com.codecool.shop.model.Genre;
import com.codecool.shop.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GenreDaoJdbc implements GenreDao {
    private List<Product> data = new ArrayList<>();
    private static GenreDaoJdbc instance = null;

    private GenreDaoJdbc() {
    }

    public static GenreDaoJdbc getInstance() {
        if (instance == null) {
            instance = new GenreDaoJdbc();
        }
        return instance;
    }


    @Override
    public void add(Genre category) {
        Connection cursor = SQLConnection.getDb();
        String genreName = category.getName();
        String insertQuery = "INSERT INTO genre (name) SELECT ? WHERE NOT EXISTS(SELECT name FROM genre WHERE name = ?)";

        try {
            PreparedStatement prepAdd = cursor.prepareStatement(insertQuery,
                    ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
            prepAdd.setString(1,genreName);
            prepAdd.setString(2,genreName);
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
        Connection cursor = SQLConnection.getDb();

        String query = "SELECT name FROM genre WHERE id = ?";
        try {
            PreparedStatement prepAdd = cursor.prepareStatement(query,
                    ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
            prepAdd.setInt(1, id);
            ResultSet rs = prepAdd.executeQuery();
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
    public Integer findByName(String genreName) {
        Connection cursor = SQLConnection.getDb();

        String query = "SELECT id FROM genre WHERE name = ?";
        try {
            PreparedStatement prepAdd = cursor.prepareStatement(query,
                    ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
            prepAdd.setString(1, genreName);
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
    public void remove(int genreId) {
        Connection cursor = SQLConnection.getDb();

        String deleteQuery = "DELETE FROM genre WHERE id = ?";
        try {
            PreparedStatement prepAdd = cursor.prepareStatement(deleteQuery,
                    ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
            prepAdd.setInt(1, genreId);
            prepAdd.execute();
            System.out.println( "Genre with genre id:" + genreId + "was deleted");
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
        Connection cursor = SQLConnection.getDb();

        List<Genre> allGenre = new ArrayList<>();
        String query = "SELECT * FROM genre";
        try {
            PreparedStatement prepAdd = cursor.prepareStatement(query,
                    ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = prepAdd.executeQuery();
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

