package com.codecool.shop.dao;

import com.codecool.shop.model.Author;

import java.util.List;

public interface AuthorDao {

    void add(Author author) throws DataSourceException;
    Integer findByName(String authorName);
    Author find(int id);
    void remove(int id);

    List<Author> getAll();
}
