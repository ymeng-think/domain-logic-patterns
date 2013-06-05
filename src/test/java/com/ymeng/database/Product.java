package com.ymeng.database;

import com.ymeng.pattern.database.InsertRowException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Product {

    private static final String insertProductStatement =
            "INSERT INTO product(id, name, type)"
                    + " VALUES(?, ?, ?)";
    private static final String findAllProductsStatement =
            "SELECT * FROM product";

    private Connection db;

    public Product(Connection db) {
        this.db = db;
    }

    public void insert(long productID, String name, String type) {
        PreparedStatement command = null;
        try {
            command = db.prepareStatement(insertProductStatement);
            command.setLong(1, productID);
            command.setString(2, name);
            command.setString(3, type);

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
        return DatabaseOperator.findAll(db, findAllProductsStatement);
    }
}
