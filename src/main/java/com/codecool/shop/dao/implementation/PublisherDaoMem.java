package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.PublisherDao;
import com.codecool.shop.model.Publisher;

import java.util.ArrayList;
import java.util.List;

public class PublisherDaoMem implements PublisherDao {

    private List<Publisher> data = new ArrayList<>();
    private static PublisherDaoMem instance = null;

    /* A private Constructor prevents any other class from instantiating.
     */
    private PublisherDaoMem() {
    }

    public static PublisherDaoMem getInstance() {
        if (instance == null) {
            instance = new PublisherDaoMem();
        }
        return instance;
    }

    @Override
    public void add(Publisher publisher) {
        publisher.setId(data.size() + 1);
        data.add(publisher);
    }

    @Override
    public Publisher find(int id) {
        return data.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
    }

    @Override
    public void remove(int id) {
        data.remove(find(id));
    }

    @Override
    public List<Publisher> getAll() {
        return data;
    }
}
