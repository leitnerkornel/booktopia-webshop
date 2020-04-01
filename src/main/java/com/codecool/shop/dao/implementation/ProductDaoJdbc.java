package com.codecool.shop.dao.implementation;

import com.codecool.shop.config.SQLConnection;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Author;
import com.codecool.shop.model.Genre;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.Recommender;
import com.codecool.shop.dao.implementation.GenreDaoJdbc;

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
        data.add(product);
        Connection cursor = SQLConnection.getDb();
        String query = "INSERT INTO book (author_id, genre_id, recommender_id, title, description, price) SELECT ?, ?, ?, ?, ?, ? WHERE NOT EXISTS (SELECT ? FROM book WHERE title = ?)";

        try {
            PreparedStatement prepAdd = cursor.prepareStatement(query,
                    ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
            prepAdd.setInt(1, product.getAuthor());
            //String genreName = product.getGenre().getName();
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
        String query = "SELECT * FROM book";
        try {
            PreparedStatement prepAdd = cursor.prepareStatement(query,
                    ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = prepAdd.executeQuery();
            while (rs.next()) {
                //Product currentProduct = new Product(rs.getInt("id"), rs.getInt("author_id"), rs.getInt("genre_id"), rs.getInt("recommender_id"), rs.getString("title"), rs.getString("description"), Math.round(rs.getFloat("price")), rs.getInt("stock"));
                //allProducts.add(currentProduct);
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

