package com.ymeng.orm;

import com.ymeng.database.DatabaseTest;
import com.ymeng.orm.dummy.Product;
import org.junit.Ignore;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ProductPersistenceTest extends DatabaseTest {

    private com.ymeng.database.Product productTable;

    @Override
    public void setUp() throws Exception {
        super.setUp();

        productTable = new com.ymeng.database.Product(connection);
    }

    @Test
    @Ignore
    public void should_persist_product_to_database() throws SQLException {
        Product product = new Product("MS Word", "W");

        product.save();

        ResultSet resultSet = productTable.findAll();
        assertThat(resultSet.next(), is(true));
    }

}
