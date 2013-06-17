package com.ymeng.orm.strategy;

import com.ymeng.orm.FlatObject;
import com.ymeng.pattern.database.QueryException;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class DataQueryStrategy {

    private final Connection connection;
    private final FlatObject template;

    public DataQueryStrategy(Connection connection, FlatObject template) {
        this.connection = connection;
        this.template = template;
    }

    public FlatObject execute() {
        PreparedStatement command = null;
        try {
            System.out.println(generateQueryStatement());
            command = connection.prepareStatement(generateQueryStatement());
            setParameters(command);
            ResultSet resultSet = command.executeQuery();
            return mapFlatObjectFrom(resultSet);
        } catch (SQLException e) {
            throw new QueryException();
        } finally {
            close(command);
        }
    }

    private String generateQueryStatement() {
        StringBuilder statement = new StringBuilder();
        statement.append("SELECT * FROM ")
                .append(template.tableName());
        appendFilterToStatement(statement);

        return statement.toString();
    }

    private void appendFilterToStatement(StringBuilder statement) {
        statement.append(" WHERE ");
        int index = 0;
        for (String key : template.primaryKeys()) {
            statement.append(key).append(" = ?");
            if (index++ < (template.primaryKeys().size() - 1)) {
                statement.append(" and ");
            }
        }
    }

    private void setParameters(PreparedStatement command) throws SQLException {
        Set<String> keys = template.primaryKeys();
        int columnIndex = 1;
        for (String key : keys) {
            command.setObject(columnIndex++, template.value(key));
        }
    }

    private FlatObject mapFlatObjectFrom(ResultSet resultSet) throws SQLException {
        FlatObject result = template.clone();

        Set<String> columnNames = extractColumnNames(resultSet);
        resultSet.next();
        for (String columnName : columnNames) {
            result.registerField(columnName, resultSet.getObject(columnName));
        }

        return result;
    }

    private Set<String> extractColumnNames(ResultSet resultSet) throws SQLException {
        ResultSetMetaData metaData = resultSet.getMetaData();
        Set<String> columnNames = new HashSet<String>();
        for (int i = 1; i <= metaData.getColumnCount(); i++) {
            columnNames.add(metaData.getColumnName(i));
        }

        return columnNames;
    }

    private static void close(PreparedStatement command) {
        try {
            command.close();
        } catch (SQLException e) {}
    }

}
