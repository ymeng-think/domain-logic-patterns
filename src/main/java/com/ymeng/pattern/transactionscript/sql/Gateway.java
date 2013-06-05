package com.ymeng.pattern.transactionscript.sql;

import com.ymeng.pattern.database.InsertRowException;
import com.ymeng.pattern.database.QueryException;
import com.ymeng.pattern.common.Money;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import static com.ymeng.pattern.transactionscript.sql.SQLCommand.FIND_CONTRACT_STATEMENT;
import static com.ymeng.pattern.transactionscript.sql.SQLCommand.FIND_RECOGNITIONS_STATEMENT;
import static com.ymeng.pattern.transactionscript.sql.SQLCommand.INSERT_RECOGNITION;

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
            command.setDate(2, convertToSqlDate(asof));

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

    public void insertRecognition(long contractID, Money money, Date recognitionDate) {
        try {
            command = db.prepareStatement(INSERT_RECOGNITION);
            command.setLong(1, contractID);
            command.setDouble(2, money.value());
            command.setDate(3, convertToSqlDate(recognitionDate));

            saveBy(command);
        } catch (SQLException e) {
            throw new InsertRowException();
        }
    }

    private java.sql.Date convertToSqlDate(Date date) {
        return new java.sql.Date(date.getTime());
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

    private void saveBy(PreparedStatement command) throws SQLException {
        command.execute();
    }
}
