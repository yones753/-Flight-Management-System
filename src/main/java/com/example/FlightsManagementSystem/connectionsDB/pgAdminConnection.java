package com.example.FlightsManagementSystem.connectionsDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class pgAdminConnection {
    Connection connection = null;
    Statement stm = null;

    public Connection getConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Flights", "postgres", "Yones12!");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    public Statement getStatement() {
        try {
            stm = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stm;
    }

}
