package com.ymeng.pattern.database;

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

    public void insert(long contractID, long productID, double revenue, Date dateSigned) {
        try {
            PreparedStatement command = db.prepareStatement(insertContractStatement);
            command.setLong(1, contractID);
            command.setLong(2, productID);
            command.setDouble(3, revenue);
            command.setDate(4, new java.sql.Date(dateSigned.getTime()));

            command.execute();
        } catch (SQLException e) {
            throw new InsertRowException();
        }
    }
}
