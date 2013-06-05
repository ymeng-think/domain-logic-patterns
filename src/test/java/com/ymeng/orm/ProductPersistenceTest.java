package com.ymeng.orm;

import org.junit.Test;

public class ProductPersistenceTest {

    @Test
    public void should_persist_product_to_database() {
        DummyProduct product = new DummyProduct("MS Word", "W");

        product.save();
    }

}
