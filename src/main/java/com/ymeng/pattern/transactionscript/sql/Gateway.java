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
    private PreparedStatement command;
    private ResultSet lastQueryResultSet;

    public Gateway(Connection db) {
        this.db = db;
    }

    public ResultSet findRecognitionsFor(long contractID, Date asof) {
        try {
            command = db.prepareStatement(FIND_RECOGNITIONS_STATEMENT);
            command.setLong(1, contractID);
            command.setDate(2, new java.sql.Date(asof.getTime()));

            return queryBy(command);
        } catch (SQLException e) {
            throw new QueryException();
        }
    }

    public ResultSet findContract(long contractID) {
        try {
            command = db.prepareStatement(FIND_CONTRACT_STATEMENT);
            command.setLong(1, contractID);

            return queryBy(command);
        } catch (SQLException e) {
            throw new QueryException();
        }
    }

    public void close() {
        if (command != null) {
            try {
                command.close();
            } catch (SQLException e) {}
        }
        if (lastQueryResultSet != null) {
            try {
                lastQueryResultSet.close();
            } catch (SQLException e) {}
        }
    }

    private ResultSet queryBy(PreparedStatement command) throws SQLException {
        lastQueryResultSet = command.executeQuery();
        return lastQueryResultSet;
    }
}
