package com.ymeng.orm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static java.text.MessageFormat.format;

public class Database {

    private static final ThreadLocal<Database> threadLocal = new ThreadLocal<Database>() {
        @Override
        protected Database initialValue() {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new JDBCDriverLoadFailedException();
            }

            final String databaseName = "domain_logic_patterns";
            final String userName = "root";
            final String password = "";
            Connection connection;
            try {
                connection = DriverManager.getConnection(getConnectionString(databaseName, userName, password));
            } catch (SQLException e) {
                throw new DBConnectionFailedException();
            }

            return new Database(connection);
        }

        private String getConnectionString(String databaseName, String userName, String password) {
            return format("jdbc:mysql://localhost/{0}?user={1}&password={2}",
                    databaseName, userName, password);
        }
    };

    private final Connection connection;

    public Database(Connection connection) {
        this.connection = connection;
    }

    public static Database current() {
        return threadLocal.get();
    }

    public void save(Object obj) {
    }

}
