package com.codecool.shop.dao.implementation;

import com.codecool.shop.config.SQLConnection;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Genre;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.Recommender;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductDaoJdbc implements ProductDao {
    private List<Product> data = new ArrayList<>();
    private static ProductDaoJdbc instance = null;

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
        data.add(product);
        Connection cursor = SQLConnection.getDb();
        String query = "INSERT INTO book (author_id, genre_id, recommender_id, title, description, price) SELECT ?, ?, ?, ?, ?, ? WHERE NOT EXISTS (SELECT ? FROM book WHERE title = ?)";

        try {
            PreparedStatement prepAdd = cursor.prepareStatement(query,
                    ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
            prepAdd.setInt(1, product.getAuthor());
            prepAdd.setInt(2, product.getGenre());
            prepAdd.setInt(3, product.getRecommender());
            prepAdd.setString(4, product.getName());
            prepAdd.setString(5, product.getDescription());
            prepAdd.setFloat(6, product.getDefaultPrice());
            prepAdd.setString(7, product.getName());
            prepAdd.setString(8, product.getName());

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
    public Product find(int id) {
        return data.stream()
                .filter(t -> t.getId() == id)
                .findFirst().orElse(null);
    }

    @Override
    public void remove(int id) {
        data.remove(find(id));
    }

    @Override
    public List<Product> getAll() {
        Connection cursor = SQLConnection.getDb();

        List<Product> allProducts = new ArrayList<>();
        String query = "SELECT\n" +
                "    book.id,\n" +
                "    book.title,\n" +
                "    book.description,\n" +
                "    book.price,\n" +
                "    book.stock,\n" +
                "    a.id AS authorID,\n" +
                "    a.name AS authorName,\n" +
                "    g.id AS genreID,\n" +
                "    g.name AS genreName,\n" +
                "    r.id AS recommenderID,\n" +
                "    r.name AS recommenderName\n" +
                "FROM book\n" +
                "    JOIN author a ON book.author_id = a.id\n" +
                "    JOIN genre g ON book.genre_id = g.id\n" +
                "    JOIN recommender r ON book.recommender_id = r.id;";
        try {
            PreparedStatement prepAdd = cursor.prepareStatement(query,
                    ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = prepAdd.executeQuery();
            while (rs.next()) {
                Product currentProduct = new Product(rs.getString("title"), rs.getString("description"), rs.getInt("id"), rs.getFloat("price"), rs.getInt("stock"), rs.getInt("authorID"), rs.getString("authorName"), rs.getInt("genreID"), rs.getString("genreName"), rs.getInt("recommenderID"), rs.getString("recommenderName"));
                allProducts.add(currentProduct);
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
        return allProducts;
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

