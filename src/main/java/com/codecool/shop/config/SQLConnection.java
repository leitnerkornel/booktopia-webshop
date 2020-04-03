package com.codecool.shop.config;


import java.io.FileReader;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
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
        /*try
        {
            Properties props = new Properties();

            String dbSettingsPropertyFile = "src/main/resources/connection.properties";
            FileReader fReader = new FileReader(dbSettingsPropertyFile);

            props.load(fReader);

            String dbDriverClass = props.getProperty("db.driver.class");

            String dbConnUrl = props.getProperty("url");

            String dbUserName = props.getProperty("user");

            String dbPassword = props.getProperty("password");

            if(!"".equals(dbDriverClass) && !"".equals(dbConnUrl))
            {
                Class.forName(dbDriverClass);

                Connection dbConn = DriverManager.getConnection(dbConnUrl, dbUserName, dbPassword);

                DatabaseMetaData dbMetaData = dbConn.getMetaData();

                String dbName = dbMetaData.getDatabaseProductName();

                String dbVersion = dbMetaData.getDatabaseProductVersion();

                System.out.println("Database Name : " + dbName);

                System.out.println("Database Version : " + dbVersion);
            }

        }catch(Exception ex)
        {
            ex.printStackTrace();
        }*/
    }

    public static Connection getDb() {
        if (instance == null) {
            instance = new SQLConnection();
        }
        instance.connectToDb();
        return cursor;
    }


}
