package com.ymeng.pattern.transactionscript;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Gateway {

    private static final String findRecognitionsStatement =
            "SELECT amount "
            + " FROM revenue_recognition"
            + " WHERE contract = ? AND recognized_on <= ?";

    private Connection db;

    public Gateway(Connection db) {
        this.db = db;
    }

    public ResultSet findRecognitionsFor(long contractID, Date asof) {
        PreparedStatement command = null;
        try {
            command = db.prepareStatement(findRecognitionsStatement);
            command.setLong(1, contractID);
            command.setDate(2, new java.sql.Date(asof.getTime()));

            return command.executeQuery();
        } catch (SQLException e) {
            throw new QueryException();
        }
    }
}
