package com.codecool.shop.dao.implementation;

import com.codecool.shop.model.Author;
import com.codecool.shop.model.Genre;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class GenreDaoJdbcTest {
    GenreDaoJdbc test = GenreDaoJdbc.getInstance();

    @Test
    void testFindByName() {
        assertEquals(1, test.findByName("Thriller"));
    }

    @Test
    void testGetAllReturnsArraylist() {
        assertEquals(true, test.getAll() instanceof ArrayList);
    }

    @Test
    void testGetAllElementsReturnsGenres() {
        assertEquals(true, test.getAll().get(1) instanceof Genre);
    }

    @Test
    void testAdd() {
        Genre testGenre = new Genre("Funny Horror");
        test.add(testGenre);
        int id = test.findByName("Funny Horror");
        assertEquals("Funny Horror", test.find(id).getName());
        test.remove(test.findByName("Funny Horror"));
    }
}