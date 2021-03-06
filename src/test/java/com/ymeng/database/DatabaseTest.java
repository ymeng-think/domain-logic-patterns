package com.ymeng.database;

import org.junit.After;
import org.junit.Before;

import java.sql.Connection;

import static com.ymeng.database.DatabaseOperator.cleanAllTables;
import static com.ymeng.database.DatabaseOperator.connect;

public abstract class DatabaseTest {

    protected Connection connection;

    @Before
    public void setUp() throws Exception {
        connection = connect();
        cleanAllTables(connection);
    }

    @After
    public void tearDown() throws Exception {
        connection.close();
    }
}
