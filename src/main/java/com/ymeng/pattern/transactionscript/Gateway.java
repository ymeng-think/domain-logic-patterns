package com.ymeng.pattern.transactionscript;

import com.ymeng.pattern.database.QueryException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Gateway {

    private static final String findRecognitionsStatement =
            "SELECT amount"
            + " FROM revenue_recognition"
            + " WHERE contract = ? AND recognized_on <= ?";

    private static final String findContractStatement =
            "SELECT revenue, date_signed, type"
            + " FROM contract INNER JOIN product ON contract.product = product.id"
            + " WHERE contract.id = ?";

    private Connection db;

    public Gateway(Connection db) {
        this.db = db;
    }

    public ResultSet findRecognitionsFor(long contractID, Date asof) {
        try {
            PreparedStatement command = db.prepareStatement(findRecognitionsStatement);
            command.setLong(1, contractID);
            command.setDate(2, new java.sql.Date(asof.getTime()));

            return command.executeQuery();
        } catch (SQLException e) {
            throw new QueryException();
        }
    }

    public ResultSet findContract(long contractID) {
        try {
            PreparedStatement command = db.prepareStatement(findContractStatement);
            command.setLong(1, contractID);

            return command.executeQuery();
        } catch (SQLException e) {
            throw new QueryException();
        }
    }
}
