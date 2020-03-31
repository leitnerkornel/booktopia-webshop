package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.RecommenderDao;
import com.codecool.shop.model.Recommender;

import java.util.ArrayList;
import java.util.List;

public class RecommenderDaoMem implements RecommenderDao {

    private List<Recommender> data = new ArrayList<>();
    private static RecommenderDaoMem instance = null;

    /* A private Constructor prevents any other class from instantiating.
     */
    private RecommenderDaoMem() {
    }

    public static RecommenderDaoMem getInstance() {
        if (instance == null) {
            instance = new RecommenderDaoMem();
        }
        return instance;
    }

    @Override
    public void add(Recommender recommender) {
        recommender.setId(data.size() + 1);
        data.add(recommender);
    }

    @Override
    public Recommender find(int id) {
        return data.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
    }

    @Override
    public void remove(int id) {
        data.remove(find(id));
    }

    @Override
    public List<Recommender> getAll() {
        return data;
    }
}
