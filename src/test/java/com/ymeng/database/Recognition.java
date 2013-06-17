package com.ymeng.database;

import com.ymeng.pattern.database.InsertRowException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Recognition {

    private static final String insertRecognitionStatement =
            "INSERT INTO revenue_recognition(contract, amount, recognized_on)"
                    + " VALUES(?, ?, ?)";
    private static final String findAllRecognitionStatement =
            "SELECT contract, amount, recognized_on"
                    + " FROM revenue_recognition"
                    + " ORDER BY contract, recognized_on";

    private Connection db;

    public Recognition(Connection db) {
        this.db = db;
    }

    public void insert(int contractID, double amount, Date recognizedOn) {
        PreparedStatement command = null;
        try {
            command = db.prepareStatement(insertRecognitionStatement);
            command.setInt(1, contractID);
            command.setDouble(2, amount);
            command.setDate(3, new java.sql.Date(recognizedOn.getTime()));

            command.execute();
        } catch (SQLException e) {
            throw new InsertRowException();
        } finally {
            if (command != null) {
                try {
                    command.close();
                } catch (SQLException e) {}
            }
        }
    }

    public ResultSet findAll() {
        PreparedStatement command = null;
        try {
            command = db.prepareStatement(findAllRecognitionStatement);

            return command.executeQuery();
        } catch (SQLException e) {
            throw new InsertRowException();
        }
    }
}
