package com.example.FlightsManagementSystem.dao;

import com.example.FlightsManagementSystem.connectionsDB.pgAdminConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class BaseDAO implements Idao {
    com.example.FlightsManagementSystem.connectionsDB.pgAdminConnection pgAdminConnection = new pgAdminConnection();
    protected Connection connection = null;
    protected Statement stm = null;

    protected void open() {
        connection = pgAdminConnection.getConnection();
        stm = pgAdminConnection.getStatement();
    }

    public  void closeAll(){
        try {
            connection.close();
            stm.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
