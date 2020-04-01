package com.codecool.shop.dao.implementation;

import com.codecool.shop.config.SQLConnection;
import com.codecool.shop.dao.RecommenderDao;
import com.codecool.shop.model.Genre;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.Recommender;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RecommenderDaoJdbc implements RecommenderDao {
    private List<Product> data = new ArrayList<>();
    private static RecommenderDaoJdbc instance = null;

    private RecommenderDaoJdbc() {
    }

    public static RecommenderDaoJdbc getInstance() {
        if (instance == null) {
            instance = new RecommenderDaoJdbc();
        }
        return instance;
    }


    @Override
    public void add(Recommender recommender) {
        Connection cursor = SQLConnection.getDb();
        String recommenderName = recommender.getName();
        String insertQuery = "INSERT INTO recommender (name) SELECT ? WHERE NOT EXISTS(SELECT name FROM recommender WHERE name = ?)";

        try {
            PreparedStatement prepAdd = cursor.prepareStatement(insertQuery,
                    ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
            prepAdd.setString(1,recommenderName);
            prepAdd.setString(2,recommenderName);
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
    public Integer findByName(String recommenderName) {
        Connection cursor = SQLConnection.getDb();

        String query = "SELECT id FROM recommender WHERE name = ?";
        try {
            PreparedStatement prepAdd = cursor.prepareStatement(query,
                    ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
            prepAdd.setString(1, recommenderName);
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
    public Recommender find(int id) {
        Connection cursor = SQLConnection.getDb();

        String query = "SELECT name FROM recommender WHERE id = ?";
        try {
            PreparedStatement prepAdd = cursor.prepareStatement(query,
                    ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
            prepAdd.setInt(1, id);
            ResultSet rs = prepAdd.executeQuery();
            while (rs.next()) {
                String recommenderName = rs.getString("name");
                return new Recommender(recommenderName);
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
    public void remove(int recommenderId) {
        Connection cursor = SQLConnection.getDb();

        String deleteQuery = "DELETE FROM recommender WHERE id = ?";
        try {
            PreparedStatement prepAdd = cursor.prepareStatement(deleteQuery,
                    ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
            prepAdd.setInt(1, recommenderId);
            prepAdd.execute();
            System.out.println( "Recommender with recommender id:" + recommenderId + "was deleted");
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
    public List<Recommender> getAll() {
        Connection cursor = SQLConnection.getDb();

        List<Recommender> allRecommenders = new ArrayList<>();
        String query = "SELECT * FROM recommender";
        try {
            PreparedStatement prepAdd = cursor.prepareStatement(query,
                    ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = prepAdd.executeQuery();
            while (rs.next()) {
                String recommenderName = rs.getString("name");
                allRecommenders.add(new Recommender(recommenderName));
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
        return allRecommenders;
    }
}
