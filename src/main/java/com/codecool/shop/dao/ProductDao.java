package com.codecool.shop.dao;

import com.codecool.shop.model.Publisher;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.Genre;

import java.util.List;

public interface ProductDao {

    void add(Product product);
    Product find(int id);
    void remove(int id);

    List<Product> getAll();
    List<Product> getBy(Publisher publisher);
    List<Product> getBy(Genre genre);

}
