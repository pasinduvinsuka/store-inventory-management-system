package com.example.javacw;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class database {

    public Connection connectDB() {

        Connection connection;
        try {
            String databaseName = "dvd";
            String url = "jdbc:mysql://localhost:3306/" + databaseName;
            String dbUsername = "root";
            String dbPassword = "password";


            connection = DriverManager.getConnection(url, dbUsername, dbPassword);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return connection;
    }


}
