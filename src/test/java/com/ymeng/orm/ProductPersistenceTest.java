package com.ymeng.orm;

import com.ymeng.database.DatabaseTest;
import com.ymeng.orm.dummy.Product;
import org.junit.Ignore;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.ymeng.matcher.Matchers.nextRowContains;
import static com.ymeng.matcher.Matchers.noMoreRow;
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
    public void should_persist_product_to_database() throws SQLException {
        Product product = new Product("MS Word", "W");

        product.save();

        ResultSet resultSet = productTable.findAll();
        assertThat(resultSet, nextRowContains(1, "MS Word", "W"));
        assertThat(resultSet, is(noMoreRow()));
    }

    @Test
    @Ignore
    public void should_load_product_from_database() {
        Product product = new Product("MS Word", "W");
        product.save();

        Product reloadedProduct = Product.load(1);

        assertThat(reloadedProduct, is(product));
    }

}
