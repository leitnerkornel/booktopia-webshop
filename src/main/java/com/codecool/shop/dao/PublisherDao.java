package com.codecool.shop.dao;

import com.codecool.shop.model.Recommender;

import java.util.List;

public interface PublisherDao {

    void add(Recommender recommender);
    Recommender find(int id);
    void remove(int id);

    List<Recommender> getAll();
}
