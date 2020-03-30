package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Genre;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.Recommender;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductDaoJdbc implements ProductDao {
    private List<Product> data = new ArrayList<>();
    private static ProductDaoJdbc instance = null;
    String dbUsername = "postgres";
    String userPassword = "postgres";
    String dbUrl = "jdbc:postgresql://localhost/codecoolshop";

    /* A private Constructor prevents any other class from instantiating.
     */
    private ProductDaoJdbc() {
    }

    public static ProductDaoJdbc getInstance() {
        if (instance == null) {
            instance = new ProductDaoJdbc();
        }
        return instance;
    }

    @Override
    public void add(Product product) {

        String sqlInsert = "INSERT INTO author (name) " +
                "VALUES (?)";

        /*PreparedStatement pstmt = conn.prepareStatement(sqlInsert);

        pstmt.setString(1, author.getFirstName());
        pstmt.setString(2, author.getLastName());
        pstmt.setDate(3, author.getBirthDate());*/

    }

    @Override
    public Product find(int id) {
        return data.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
    }

    @Override
    public void remove(int id) {
        data.remove(find(id));
    }

    @Override
    public List<Product> getAll() {
        return data;
    }

    @Override
    public List<Product> getBy(Recommender recommender) {
        return data.stream().filter(t -> t.getRecommender().equals(recommender)).collect(Collectors.toList());
    }

    @Override
    public List<Product> getBy(Genre genre) {
        return data.stream().filter(t -> t.getGenre().equals(genre)).collect(Collectors.toList());
    }
}

