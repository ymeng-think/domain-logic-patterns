package com.ymeng.pattern.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

public class Recognition {

    private static final String insertRecognitionStatement =
            "INSERT INTO revenue_recognition(contract, amount, recognized_on)"
                    + " VALUES(?, ?, ?)";

    private Connection db;

    public Recognition(Connection db) {
        this.db = db;
    }

    public void insert(long contractID, double amount, Date recognizedOn) {
        try {
            PreparedStatement command = db.prepareStatement(insertRecognitionStatement);
            command.setLong(1, contractID);
            command.setDouble(2, amount);
            command.setDate(3, new java.sql.Date(recognizedOn.getTime()));

            command.execute();
        } catch (SQLException e) {
            throw new InsertRowException();
        }

    }
}
