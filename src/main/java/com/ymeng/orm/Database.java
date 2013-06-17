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


    public static final long INVALID_ID = -1;
    private final Connection connection;

    public Database(Connection connection) {
        this.connection = connection;
    }

    public static Database current() {
        return threadLocal.get();
    }

    public void save(Object obj) {
        Flattener flattener = new Flattener(obj);
        flattener.flatten().save(connection);
    }

    public <T> T loadById(Class<T> clazz, long id) {
        FlatObject flatObject = FlatObjectCollection.load(connection, clazz, id);
        Extruder<T> extruder = new Extruder<T>(flatObject);
        return extruder.extrude();
    }


}
