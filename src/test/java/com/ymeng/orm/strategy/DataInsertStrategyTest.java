package com.ymeng.orm.strategy;

import com.ymeng.database.DatabaseTest;
import com.ymeng.database.Product;
import com.ymeng.orm.FlatObject;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.ymeng.matcher.Matchers.nextRowContains;
import static com.ymeng.orm.Database.INVALID_ID;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class DataInsertStrategyTest extends DatabaseTest {

    private Product productTable;
    private FlatObject newProduct;

    @Override
    public void setUp() throws Exception {
        super.setUp();

        newProduct = new FlatObject("product");
        newProduct.registerPrimaryKeys("id");
        newProduct.registerField("id", INVALID_ID);
        newProduct.registerField("name", "MS Word");
        newProduct.registerField("type", "W");

        productTable = new Product(connection);
    }

    @Test
    public void should_insert_object_into_database() throws SQLException {
        DataInsertStrategy strategy = new DataInsertStrategy(connection, newProduct);

        strategy.execute();

        ResultSet resultSet = productTable.findAll();
        assertThat(resultSet, nextRowContains(1, "MS Word", "W"));
        assertThat(resultSet.next(), is(false));
    }
}
