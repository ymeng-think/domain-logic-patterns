package com.ymeng.pattern.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static java.text.MessageFormat.format;

public final class DatabaseOperator {

    private static final String databaseName = "domain_logic_patterns";
    private static final String userName = "root";
    private static final String password = "";

    private static final String truncateTableStatement = "truncate table {0}";

    public static Connection connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection(getConnectionString(databaseName, userName, password));
        } catch (ClassNotFoundException e) {
            throw new ConnectFailedException();
        } catch (SQLException e) {
            throw new ConnectFailedException();
        }
    }

    public static void cleanAllTables(Connection connection) {
        String[] tables = {"product", "contract", "revenue_recognition"};

        try {
            Statement command = connection.createStatement();
            for (String table : tables) {
                command.execute(format(truncateTableStatement, table));
            }
        } catch (SQLException e) {
            throw new TruncateTableException();
        }
    }

    private DatabaseOperator() {
    }

    private static String getConnectionString(String databaseName, String userName, String password) {
        return format("jdbc:mysql://localhost/{0}?user={1}&password={2}",
                databaseName, userName, password);
    }

}
