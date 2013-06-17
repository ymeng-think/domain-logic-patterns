package com.ymeng.orm.strategy;

import com.ymeng.database.DatabaseTest;
import com.ymeng.database.Product;
import com.ymeng.orm.FlatObject;
import org.junit.Test;

import static com.ymeng.matcher.Matchers.contains;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class DataQueryStrategyTest extends DatabaseTest {

    @Override
    public void setUp() throws Exception {
        super.setUp();

        Product product = new Product(connection);
        product.insert(1, "MS Word", "W");
    }

    @Test
    public void should_find_flat_object_from_database_by_id() {
        FlatObject template = new FlatObject("Product");
        template.registerPrimaryKeys("id");
        template.registerField("id", 1L);
        DataQueryStrategy strategy = new DataQueryStrategy(connection, template);

        FlatObject flatObject = strategy.execute();

        assertThat(flatObject.tableName(), is("product"));
        assertThat(flatObject.fields().size(), is(3));
        assertThat(flatObject.fields(), contains("id", "name", "type"));
    }
}
