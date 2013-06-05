package com.ymeng.orm;

import com.ymeng.database.DatabaseTest;
import com.ymeng.database.Product;
import org.junit.Ignore;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ProductPersistenceTest extends DatabaseTest {

    private Product productTable;

    @Override
    public void setUp() throws Exception {
        super.setUp();

        productTable = new Product(connection);
    }

    @Test
    @Ignore
    public void should_persist_product_to_database() throws SQLException {
        DummyProduct product = new DummyProduct("MS Word", "W");

        product.save();

        ResultSet resultSet = productTable.findAll();
        assertThat(resultSet.next(), is(true));
    }

}
