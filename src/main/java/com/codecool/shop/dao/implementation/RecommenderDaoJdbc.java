package com.codecool.shop.dao.implementation;

import com.codecool.shop.config.SQLConnection;
import com.codecool.shop.dao.RecommenderDao;
import com.codecool.shop.model.Genre;
import com.codecool.shop.model.Recommender;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RecommenderDaoJdbc implements RecommenderDao {

    @Override
    public void add(Recommender recommender) {
        Connection cursor = SQLConnection.getDb();
        String recommenderName = recommender.getName();

        String insertQuery = "INSERT INTO recommender (name) SELECT ? WHERE NOT EXISTS(SELECT name FROM genre WHERE name = ?)";

        try {
            PreparedStatement prepAdd = cursor.prepareStatement(insertQuery,
                    ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
            prepAdd.setString(1, recommenderName);
            prepAdd.setString(2, recommenderName);
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
    public Recommender find(int id) {
        Connection cursor = SQLConnection.getDb();

        String query = "SELECT name FROM recommender WHERE id = ${id}";
        try {
            PreparedStatement prepAdd = cursor.prepareStatement(query,
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
        return null;
    }

    @Override
    public void remove(int id) {
        Connection cursor = SQLConnection.getDb();

        String deleteQuery = "DELETE FROM recommender WHERE (SELECT id FROM genre WHERE id = ${id})";
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
    public List<Recommender> getAll() {
        Connection cursor = SQLConnection.getDb();

        List<Genre> allRecommender = new ArrayList<>();
        String query = "SELECT * FROM recommender";
        try {
            PreparedStatement prepAdd = cursor.prepareStatement(query,
                    ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = prepAdd.executeQuery(query);
            while (rs.next()) {
                String genreName = rs.getString("name");
                //allRecommender.add();
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
}
