package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.AuthorDao;
import com.codecool.shop.model.Author;
import com.codecool.shop.model.Genre;

import java.util.ArrayList;
import java.util.List;

public class AuthorDaoMem implements AuthorDao {
    private List<Author> data = new ArrayList<>();
    private static AuthorDaoMem instance = null;

    public AuthorDaoMem() {
    }

    public static AuthorDaoMem getInstance() {
        if (instance == null) {
            instance = new AuthorDaoMem();
        }
        return instance;
    }

    @Override
    public void add(Author author) {
        author.setId(data.size() + 1);
        data.add(author);
    }

    @Override
    public Integer findByName(String authorName) {
        return null;
    }

    @Override
    public Author find(int id) {
        return null;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<Author> getAll() {
        return null;
    }
}
