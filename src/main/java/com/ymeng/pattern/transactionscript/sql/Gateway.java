package com.ymeng.pattern.transactionscript.sql;

import com.ymeng.pattern.database.QueryException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import static com.ymeng.pattern.transactionscript.sql.SQLCommand.FIND_CONTRACT_STATEMENT;
import static com.ymeng.pattern.transactionscript.sql.SQLCommand.FIND_RECOGNITIONS_STATEMENT;

public class Gateway {

    private Connection db;

    public Gateway(Connection db) {
        this.db = db;
    }

    public ResultSet findRecognitionsFor(long contractID, Date asof) {
        try {
            PreparedStatement command = db.prepareStatement(FIND_RECOGNITIONS_STATEMENT);
            command.setLong(1, contractID);
            command.setDate(2, new java.sql.Date(asof.getTime()));

            return command.executeQuery();
        } catch (SQLException e) {
            throw new QueryException();
        }
    }

    public ResultSet findContract(long contractID) {
        try {
            PreparedStatement command = db.prepareStatement(FIND_CONTRACT_STATEMENT);
            command.setLong(1, contractID);

            return command.executeQuery();
        } catch (SQLException e) {
            throw new QueryException();
        }
    }
}
