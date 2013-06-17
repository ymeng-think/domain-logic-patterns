package com.ymeng.database;

import com.ymeng.pattern.database.InsertRowException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

public class Contract {

    private static final String insertContractStatement =
            "INSERT INTO contract(id, product, revenue, date_signed)"
                    + " VALUES(?, ?, ?, ?)";

    private Connection db;

    public Contract(Connection db) {
        this.db = db;
    }

    public void insert(int contractID, int productID, double revenue, Date dateSigned) {
        PreparedStatement command = null;
        try {
            command = db.prepareStatement(insertContractStatement);
            command.setInt(1, contractID);
            command.setInt(2, productID);
            command.setDouble(3, revenue);
            command.setDate(4, new java.sql.Date(dateSigned.getTime()));

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
}
