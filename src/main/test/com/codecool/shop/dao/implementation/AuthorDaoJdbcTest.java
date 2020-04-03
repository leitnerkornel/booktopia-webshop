package com.codecool.shop.dao.implementation;

import com.codecool.shop.model.Author;
import com.codecool.shop.model.Genre;
import org.junit.jupiter.api.Test;

import javax.naming.directory.InvalidAttributesException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AuthorDaoJdbcTest {
    AuthorDaoJdbc test = AuthorDaoJdbc.getInstance();

    @Test
    void testAdd() {
        Author testAuthor = new Author("test Mike");
        test.add(testAuthor);
        int id = test.findByName("test Mike");
        assertEquals("test Mike", test.find(id).getName());
        test.remove(test.findByName("test Mike"));
    }

    private static <T> void assertThrows(Class<T extends Throwable> exceptionType, Runnable callback) {
        try {
            callback.run();
        }
        catch (T e) {
            assert(true);
        } catch (Throwable e) {
            assertEquals(e.getClass(), exceptionType);
        }
        assert(false);
    }

    @Test
    void testAddWithInvalidArgument() {
        assertThrows(IllegalArgumentException.class, () -> {test.add(null);});
    }

    @Test
    void testFindByNameWithInvalidName() {
        assertEquals(null, test.findByName("No one"));
    }

    @Test
    void testGetAllElementsReturnsAuthors() {
        assertEquals(true, test.getAll().get(1) instanceof Author);
    }
}
