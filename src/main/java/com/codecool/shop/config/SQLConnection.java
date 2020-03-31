package com.codecool.shop.config;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class SQLConnection {
    private static String user;
    private static String password;
    private static String url;

    private static Connection cursor;
    private static SQLConnection instance;

    private SQLConnection () {
        user = "postgres";
        password = "postgres";
        url = "jdbc:postgresql://localhost:5432/codecoolshop";
    }

    private void connectToDb () {
        Properties property = new Properties();
        property.setProperty("user", user);
        property.setProperty("password", password);
        try {
            cursor = DriverManager.getConnection(url, property);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getDb() {
        if (instance == null) {
            instance = new SQLConnection();
        }
        instance.connectToDb();
        return cursor;
    }


}
