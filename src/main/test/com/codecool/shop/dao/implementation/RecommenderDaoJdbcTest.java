package com.codecool.shop.dao.implementation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RecommenderDaoJdbcTest {
    RecommenderDaoJdbc test = RecommenderDaoJdbc.getInstance();

    @Test
    void testFindByNameWithValidName() {
        assertEquals(3, test.findByName("Gabor"));
    }

    @Test
    void testFindByNameWithInvalidName() {
        assertEquals(null, test.findByName("Lord Vader"));

    }
}