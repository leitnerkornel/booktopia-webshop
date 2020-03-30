package com.codecool.shop.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SQLConnection {
    String dbUsername = "postgres";
    String userPassword = "postgres";
    String dbUrl = "jdbc:postgresql://localhost/codecoolshop";


    void writeIntoDatabase(String sqlInsert, PreparedStatement pstmt) {
        try (
                Connection conn = DriverManager.getConnection(dbUrl, dbUsername, userPassword);
                ) {
        } catch (
                SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
