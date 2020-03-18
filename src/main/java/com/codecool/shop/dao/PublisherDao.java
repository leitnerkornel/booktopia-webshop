package com.codecool.shop.dao;

import com.codecool.shop.model.Publisher;

import java.util.List;

public interface PublisherDao {

    void add(Publisher publisher);
    Publisher find(int id);
    void remove(int id);

    List<Publisher> getAll();
}
